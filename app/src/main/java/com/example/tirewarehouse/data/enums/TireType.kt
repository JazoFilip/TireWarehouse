package com.example.tirewarehouse.data.enums

import androidx.annotation.DrawableRes
import com.example.tirewarehouse.R

enum class TireType(@DrawableRes val imageRes: Int) {
    CAR(R.drawable.car_tire),
    TRUCK(R.drawable.tractor_tire),
    TRACTOR(R.drawable.truck_tire);


    companion object {
        fun fromString(value: String): TireType =
            values().firstOrNull { it.name == value }
                ?: CAR // default fallback
    }
}