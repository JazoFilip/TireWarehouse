package com.example.tirewarehouse.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TireDao {

    @Query("SELECT * FROM tires")
    fun getAllTiresFlow(): Flow<List<TireEntity>>

    @Query("SELECT * FROM tires")
    suspend fun getAllTires(): List<TireEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTire(tire: TireEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tires: List<TireEntity>)

    @Query("SELECT * FROM tires WHERE tireId = :id")
    suspend fun getTireById(id: String): TireEntity?

    @Query("DELETE FROM tires WHERE tireId = :id")
    suspend fun deleteTireById(id: String)

    @Query("DELETE FROM tires")
    suspend fun clearAllTires()
}