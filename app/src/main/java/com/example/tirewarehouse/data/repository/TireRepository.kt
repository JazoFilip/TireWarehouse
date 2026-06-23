package com.example.tirewarehouse.data.repository

import com.example.tirewarehouse.data.database.TireEntity
import com.example.tirewarehouse.data.model.Tire
import kotlinx.coroutines.flow.Flow

interface TireRepository {

    fun getTiresFlow(): Flow<List<TireEntity>>

    suspend fun refreshTires()

    suspend fun createTire(tire: Tire): Boolean

    suspend fun updateTire(id: String, tire: Tire): Boolean

    suspend fun getTireDetails(id: String): Tire?

    suspend fun deleteTire(id: String): Boolean
}