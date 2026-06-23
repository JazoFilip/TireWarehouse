package com.example.tirewarehouse

import android.app.Application
import com.google.firebase.FirebaseApp


class TireWarehouseApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        container = AppDataContainer(this)
    }
}