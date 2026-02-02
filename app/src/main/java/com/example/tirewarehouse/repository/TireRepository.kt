package com.example.tirewarehouse.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tirewarehouse.data.Tire
import com.google.firebase.firestore.FirebaseFirestore


class TireRepository{
    private val db = FirebaseFirestore.getInstance()
    private val tiresRef = db.collection("tires")

    private val _tires = MutableLiveData<List<Tire>>()
    val tires: LiveData<List<Tire>> = _tires

    fun start(){
        fetchDatabaseData()
    }

    private fun fetchDatabaseData(){
        tiresRef.addSnapshotListener { snapshot, error ->
            if ( error != null) return@addSnapshotListener

            val list = snapshot?.toObjects(Tire::class.java) ?: emptyList()
            list.forEachIndexed { index,tire ->
                snapshot?.documents?.get(index)?.let { tire.id = it.id }
            }
            _tires.value = list
        }

    }

    fun addTire(tire: Tire) {
        val doc = tiresRef.document()
        doc.set(tire.copy(id = doc.id))
    }

    fun updateTireQuantity(tireId: String, amount: Int) {
        val docRef = tiresRef.document(tireId)

        db.runTransaction { transaction ->
            val snapshot = transaction.get(docRef)
            val tire = snapshot.toObject(Tire::class.java)
                ?: throw IllegalStateException("Tire not found")

            val newQuantity = (tire.quantity + amount)

            if(newQuantity <= 0){
                transaction.delete(docRef)
            }else{
                transaction.update(docRef, "quantity", newQuantity)
            }

        }
    }
}