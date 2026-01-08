package com.example.tirewarehouse.data

import com.example.tirewarehouse.data.enums.Location
import com.example.tirewarehouse.data.enums.Season
import com.example.tirewarehouse.data.enums.TireType

data class Tire (
    var id: String = "",
    val type: String = TireType.CAR.name,
    val width: Float = 0f,
    val height: Float = 0f,
    val diameter: Float = 0f,
    val season: String = Season.ALL_SEASON.name,
    val brand: String = "",
    var quantity: Int = 0,
    var location: String = Location.GRADISKA_SUSJED.name,
)