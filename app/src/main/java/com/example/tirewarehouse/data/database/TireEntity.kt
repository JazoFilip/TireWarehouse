package com.example.tirewarehouse.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "tires")
@Serializable
data class TireEntity(
    @PrimaryKey val tireId: String,
    val type: String?,
    val width: String?,
    val height: String?,
    val diameter: String?,
    val season: String?,
    val brand: String?,
    val quantity: Int,
    val isSynced: Boolean = true
)