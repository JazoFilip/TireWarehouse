package com.example.tirewarehouse.presentation.addTireScreenViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tirewarehouse.TireWarehouseApplication
import com.example.tirewarehouse.data.enums.Brand
import com.example.tirewarehouse.data.enums.Season
import com.example.tirewarehouse.data.enums.TireType
import com.example.tirewarehouse.data.model.Tire
import com.example.tirewarehouse.data.repository.TireRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddTireViewModel(private val tireRepository: TireRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<AddTireUiState>(AddTireUiState.Form())
    val uiState: StateFlow<AddTireUiState> = _uiState.asStateFlow()

    private fun updateForm(updateBlock: (AddTireUiState.Form) -> AddTireUiState.Form) {
        _uiState.update { currentState ->
            if (currentState is AddTireUiState.Form) updateBlock(currentState) else currentState
        }
    }

    fun setWidth(value: String) { updateForm { it.copy(width = value) } }
    fun setHeight(value: String) { updateForm { it.copy(height = value) } }
    fun setDiameter(value: String) { updateForm { it.copy(diameter = value) } }
    fun setType(value: TireType) { updateForm { it.copy(type = value) } }
    fun setSeason(value: Season) { updateForm { it.copy(season = value) } }
    fun setBrand(value: Brand) { updateForm { it.copy(brand = value) } }
    fun setQuantity(value: Int?) { updateForm { it.copy(quantity = value) } }

    fun addOrIncreaseTire() {
        viewModelScope.launch {
            val currentForm = _uiState.value as? AddTireUiState.Form ?: return@launch

            val newTire = Tire(
                type = currentForm.type?.name,
                width = currentForm.width.ifEmpty { null },
                height = currentForm.height.ifEmpty { null },
                diameter = currentForm.diameter.ifEmpty { null },
                season = currentForm.season?.name,
                brand = currentForm.brand?.name,
                quantity = currentForm.quantity ?: 0
            )

            _uiState.value = AddTireUiState.Loading

            try {
                val success = tireRepository.createTire(newTire)
                if (success) {
                    // RESET VALUES TO BLANK FOR CONTINUOUS ADDING
                    _uiState.value = AddTireUiState.Form()
                } else {
                    _uiState.value = AddTireUiState.Failure("Greška: Neuspješno spremanje u bazu podataka.")
                }
            } catch (e: Exception) {
                _uiState.value = AddTireUiState.Failure("Greška: ${e.localizedMessage}")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TireWarehouseApplication)
                AddTireViewModel(application.container.tireRepository)
            }
        }
    }
}