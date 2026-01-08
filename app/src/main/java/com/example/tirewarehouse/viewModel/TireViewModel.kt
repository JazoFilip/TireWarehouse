package com.example.tirewarehouse.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.tirewarehouse.data.Tire
import com.example.tirewarehouse.data.enums.TireType
import com.example.tirewarehouse.repository.TireRepository

class  TireViewModel() : ViewModel() {

    private val repository = TireRepository()

    val tires: LiveData<List<Tire>> = repository.tires
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