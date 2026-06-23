package com.example.tirewarehouse.presentation.homeScreenViewModel

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class Success(
        val totalTires: Int,
        val totalCarTires: Int,
        val totalTractorTires: Int,
        val totalTruckTires: Int
    ) : HomeUiState

    data class Error(val message: String) : HomeUiState
}