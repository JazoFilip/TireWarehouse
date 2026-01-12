package com.example.tirewarehouse.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.tirewarehouse.data.Tire
import com.example.tirewarehouse.data.enums.TireType
import com.example.tirewarehouse.repository.TireRepository

class  TireViewModel() : ViewModel() {

    private val repository = TireRepository()
    val tires: LiveData<List<Tire>> = repository.tires

    private val _width = MutableLiveData<Float?>(null)
    private val _height = MutableLiveData<Float?>(null)
    private val _diameter = MutableLiveData<Float?>(null)

    fun setWidth(value: Float?) { _width.value = value }
    fun setHeight(value: Float?) { _height.value = value }
    fun setDiameter(value: Float?) { _diameter.value = value }


    fun getFilteredTires(type: String?): LiveData<List<Tire>> {
        return MediatorLiveData<List<Tire>>().apply {

            fun update() {
                val list = tires.value ?: emptyList()
                val w = _width.value
                val h = _height.value
                val d = _diameter.value

                value = list.filter { tire ->
                    (type == null || tire.type == type) &&
                            (w == null || tire.width == w) &&
                            (h == null || tire.height == h) &&
                            (d == null || tire.diameter == d)
                }
            }

            addSource(tires) { update() }
            addSource(_width) { update() }
            addSource(_height) { update() }
            addSource(_diameter) { update() }
        }
    }
    val totalTires: LiveData<Int> = tires.map { list ->
        list.sumOf { it.quantity }
    }
    val totalCarTires: LiveData<Int> = tires.map { list ->
        list.filter { it.type == TireType.CAR.name }
            .sumOf { it.quantity }
    }
    val totalTractorTires: LiveData<Int> = tires.map { list ->
        list.filter { it.type == TireType.TRACTOR.name }
            .sumOf { it.quantity }
    }
    val totalTruckTires: LiveData<Int> = tires.map { list ->
        list.filter { it.type == TireType.TRUCK.name }
            .sumOf { it.quantity }
    }

}