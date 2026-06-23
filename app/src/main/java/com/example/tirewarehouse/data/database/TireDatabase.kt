package com.example.tirewarehouse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TireEntity::class], version = 1, exportSchema = false)
abstract class TireDatabase : RoomDatabase() {
    abstract fun tireDao(): TireDao
}