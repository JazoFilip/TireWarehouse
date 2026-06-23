package com.example.tirewarehouse.presentation.inventoryScreenViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tirewarehouse.TireWarehouseApplication
import com.example.tirewarehouse.data.database.TireEntity
import com.example.tirewarehouse.data.model.Tire
import com.example.tirewarehouse.data.repository.TireRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class InventoryViewModel(
    private val tireRepository: TireRepository,
    private val tireType: String?
) : ViewModel() {

    private val _uiState = MutableStateFlow<InventoryUiState>(InventoryUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var widthQuery = ""
    private var heightQuery = ""
    private var diameterQuery = ""
    private var cachedEntities = emptyList<TireEntity>()

    init {
        observeLocalDatabase()
    }

    private fun observeLocalDatabase() {
        viewModelScope.launch {
            tireRepository.getTiresFlow()
                .catch { e ->
                    _uiState.value = InventoryUiState.Error("Greška pri čitanju baze: ${e.localizedMessage}")
                }
                .collect { entityList ->
                    cachedEntities = entityList
                    updateFilteredList()
                }
        }
    }

    private fun updateFilteredList() {
        val filteredList = cachedEntities.filter { entity ->
            val matchesType = tireType == null || entity.type == tireType
            val matchesWidth = widthQuery.isEmpty() || entity.width?.contains(widthQuery, ignoreCase = true) == true
            val matchesHeight = heightQuery.isEmpty() || entity.height?.contains(heightQuery, ignoreCase = true) == true
            val matchesDiameter = diameterQuery.isEmpty() || entity.diameter?.contains(diameterQuery, ignoreCase = true) == true

            matchesType && matchesWidth && matchesHeight && matchesDiameter
        }.sortedBy { entity ->
            entity.diameter?.toDoubleOrNull() ?: Double.MAX_VALUE
        }

        _uiState.value = InventoryUiState.Success(
            tires = filteredList,
            searchQueryWidth = widthQuery,
            searchQueryHeight = heightQuery,
            searchQueryDiameter = diameterQuery
        )
    }

    fun updateWidth(query: String) {
        widthQuery = query
        updateFilteredList()
    }

    fun updateHeight(query: String) {
        heightQuery = query
        updateFilteredList()
    }

    fun updateDiameter(query: String) {
        diameterQuery = query
        updateFilteredList()
    }

    fun updateQuantity(tireId: String, delta: Int) {
        viewModelScope.launch {
            val currentState = _uiState.value as? InventoryUiState.Success ?: return@launch
            val currentEntity = currentState.tires.find { it.tireId == tireId } ?: return@launch

            val newQuantity = currentEntity.quantity + delta

            if (newQuantity < 0) return@launch // Double safety check

            if (newQuantity == 0) {
                tireRepository.deleteTire(tireId)
            } else {
                val updatedTire = Tire(
                    id = currentEntity.tireId,
                    type = currentEntity.type,
                    width = currentEntity.width,
                    height = currentEntity.height,
                    diameter = currentEntity.diameter,
                    season = currentEntity.season,
                    brand = currentEntity.brand,
                    quantity = newQuantity
                )
                tireRepository.updateTire(tireId, updatedTire)
            }
        }
    }

    companion object {
        fun provideFactory(tireType: String?): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TireWarehouseApplication)
                InventoryViewModel(application.container.tireRepository, tireType)
            }
        }
    }
}