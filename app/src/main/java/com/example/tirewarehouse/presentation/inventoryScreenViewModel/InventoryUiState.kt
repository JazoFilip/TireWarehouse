package com.example.tirewarehouse.presentation.inventoryScreenViewModel

import com.example.tirewarehouse.data.database.TireEntity

sealed interface InventoryUiState {
    data object Loading : InventoryUiState

    data class Success(
        val tires: List<TireEntity>,
        val searchQueryWidth: String,
        val searchQueryHeight: String,
        val searchQueryDiameter: String
    ) : InventoryUiState

    data class Error(val message: String) : InventoryUiState
}