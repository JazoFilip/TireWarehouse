package com.example.tirewarehouse.presentation.addTireScreenViewModel

import com.example.tirewarehouse.data.enums.Brand
import com.example.tirewarehouse.data.enums.Season
import com.example.tirewarehouse.data.enums.TireType

sealed interface AddTireUiState {
    data class Form(
        val width: String = "",
        val height: String = "",
        val diameter: String = "",
        val type: TireType? = null,
        val season: Season? = null,
        val brand: Brand? = null,
        val quantity: Int? = null
    ) : AddTireUiState

    data object Loading : AddTireUiState
    data class Failure(val message: String) : AddTireUiState
}