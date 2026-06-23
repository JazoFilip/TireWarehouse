package com.example.tirewarehouse.presentation.homeScreenViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tirewarehouse.TireWarehouseApplication
import com.example.tirewarehouse.data.enums.TireType
import com.example.tirewarehouse.data.repository.TireRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val tireRepository: TireRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        observeLocalDatabase()
        refreshData()
    }

    private fun observeLocalDatabase() {
        viewModelScope.launch {
            tireRepository.getTiresFlow()
                .catch { e ->
                    _uiState.value = HomeUiState.Error("Greška pri čitanju baze: ${e.localizedMessage}")
                }
                .collect { entityList ->
                    val total = entityList.sumOf { it.quantity }
                    val car = entityList.filter { it.type == TireType.CAR.name }.sumOf { it.quantity }
                    val tractor = entityList.filter { it.type == TireType.TRACTOR.name }.sumOf { it.quantity }
                    val truck = entityList.filter { it.type == TireType.TRUCK.name }.sumOf { it.quantity }

                    _uiState.value = HomeUiState.Success(
                        totalTires = total,
                        totalCarTires = car,
                        totalTractorTires = tractor,
                        totalTruckTires = truck
                    )
                }
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            try {
                tireRepository.refreshTires()
            } catch (e: Exception) {
                // If local database flow is already listening, errors during refresh
                // can either be ignored or explicitly passed to UI if critical.
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TireWarehouseApplication)
                HomeViewModel(application.container.tireRepository)
            }
        }
    }
}