package com.example.tirewarehouse

import android.content.Context
import androidx.room.Room
import com.example.tirewarehouse.data.database.TireDatabase
import com.example.tirewarehouse.data.repository.FirebaseTireRepository
import com.example.tirewarehouse.data.repository.TireRepository

interface AppContainer {
    val tireRepository: TireRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    private val database: TireDatabase by lazy {
        Room.databaseBuilder(
            context,
            TireDatabase::class.java,
            "tire_warehouse_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    override val tireRepository: TireRepository by lazy {
        FirebaseTireRepository(database.tireDao())
    }
}