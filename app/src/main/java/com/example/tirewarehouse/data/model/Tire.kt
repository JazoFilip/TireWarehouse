package com.example.tirewarehouse.data.model

import com.example.tirewarehouse.data.enums.Brand
import com.example.tirewarehouse.data.enums.Season
import com.example.tirewarehouse.data.enums.TireType

data class Tire (
    var id: String = "",
    val type: String? = null,
    val width: String? = null,
    val height: String? = null,
    val diameter: String? = null,
    val season: String? = null,
    val brand: String? = null,
    var quantity: Int = 0
)