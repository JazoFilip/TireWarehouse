package com.example.tirewarehouse.data.repository

import com.example.tirewarehouse.data.database.TireDao
import com.example.tirewarehouse.data.database.TireEntity
import com.example.tirewarehouse.data.model.Tire
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class FirebaseTireRepository(private val tireDao: TireDao) : TireRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val tiresCollection = firestore.collection("tires")

    override fun getTiresFlow(): Flow<List<TireEntity>> {
        return tireDao.getAllTiresFlow()
    }

    override suspend fun refreshTires() {
        try {
            val querySnapshot = tiresCollection.get().await()
            val remoteTiresList = mutableListOf<TireEntity>()

            for (document in querySnapshot.documents) {
                val tire = document.toObject(Tire::class.java)
                if (tire != null) {
                    tire.id = document.id
                    remoteTiresList.add(
                        TireEntity(
                            tireId = tire.id,
                            type = tire.type,
                            width = tire.width,
                            height = tire.height,
                            diameter = tire.diameter,
                            season = tire.season,
                            brand = tire.brand,
                            quantity = tire.quantity,
                            isSynced = true
                        )
                    )
                }
            }

            if (remoteTiresList.isNotEmpty()) {
                tireDao.clearAllTires()
                tireDao.insertAll(remoteTiresList)
            }
        } catch (e: Exception) {
        }
    }

    override suspend fun createTire(tire: Tire): Boolean {
        return try {
            val localTires = tireDao.getAllTires()

            val existingEntity = localTires.find { entity ->
                entity.type == tire.type &&
                        entity.width == tire.width &&
                        entity.height == tire.height &&
                        entity.diameter == tire.diameter &&
                        entity.season == tire.season &&
                        entity.brand == tire.brand
            }

            if (existingEntity != null) {
                val newQuantity = existingEntity.quantity + tire.quantity

                val updatedTire = Tire(
                    id = existingEntity.tireId,
                    type = tire.type,
                    width = tire.width,
                    height = tire.height,
                    diameter = tire.diameter,
                    season = tire.season,
                    brand = tire.brand,
                    quantity = newQuantity
                )

                updateTire(existingEntity.tireId, updatedTire)
            } else {
                val newDocRef = tiresCollection.document()
                tire.id = newDocRef.id

                val localEntity = TireEntity(
                    tireId = tire.id,
                    type = tire.type,
                    width = tire.width,
                    height = tire.height,
                    diameter = tire.diameter,
                    season = tire.season,
                    brand = tire.brand,
                    quantity = tire.quantity,
                    isSynced = false
                )
                tireDao.insertTire(localEntity)

                newDocRef.set(tire).await()
                tireDao.insertTire(localEntity.copy(isSynced = true))
                true
            }
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun updateTire(id: String, tire: Tire): Boolean {
        tire.id = id
        val localEntity = TireEntity(
            tireId = id,
            type = tire.type,
            width = tire.width,
            height = tire.height,
            diameter = tire.diameter,
            season = tire.season,
            brand = tire.brand,
            quantity = tire.quantity,
            isSynced = false
        )
        tireDao.insertTire(localEntity)

        return try {
            tiresCollection.document(id).set(tire).await()
            tireDao.insertTire(localEntity.copy(isSynced = true))
            true
        } catch (e: Exception) {
            true
        }
    }

    override suspend fun getTireDetails(id: String): Tire? {
        return try {
            val documentSnapshot = tiresCollection.document(id).get().await()
            val remoteTire = documentSnapshot.toObject(Tire::class.java)
            if (remoteTire != null) {
                remoteTire.id = documentSnapshot.id

                // Osvježi i Room bazu usput
                tireDao.insertTire(
                    TireEntity(
                        tireId = remoteTire.id,
                        type = remoteTire.type,
                        width = remoteTire.width,
                        height = remoteTire.height,
                        diameter = remoteTire.diameter,
                        season = remoteTire.season,
                        brand = remoteTire.brand,
                        quantity = remoteTire.quantity,
                        isSynced = true
                    )
                )
                remoteTire
            } else {
                getTireFromLocalFallback(id)
            }
        } catch (e: Exception) {
            getTireFromLocalFallback(id)
        }
    }

    private suspend fun getTireFromLocalFallback(id: String): Tire? {
        val localEntity = tireDao.getTireById(id) ?: return null
        return Tire(
            id = localEntity.tireId,
            type = localEntity.type,
            width = localEntity.width,
            height = localEntity.height,
            diameter = localEntity.diameter,
            season = localEntity.season,
            brand = localEntity.brand,
            quantity = localEntity.quantity
        )
    }

    override suspend fun deleteTire(id: String): Boolean {
        tireDao.deleteTireById(id)
        return try {
            tiresCollection.document(id).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }
}