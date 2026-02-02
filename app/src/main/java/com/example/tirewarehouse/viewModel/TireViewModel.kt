package com.example.tirewarehouse.viewModel

import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.tirewarehouse.data.Tire
import com.example.tirewarehouse.data.enums.Brand
import com.example.tirewarehouse.data.enums.Location
import com.example.tirewarehouse.data.enums.Season
import com.example.tirewarehouse.data.enums.TireType
import com.example.tirewarehouse.repository.TireRepository

class  TireViewModel() : ViewModel() {

    private val repository = TireRepository()
    val tires: LiveData<List<Tire>> = repository.tires

    private val _type = MutableLiveData<TireType?>(null)
    private val _width = MutableLiveData<String?>(null)
    private val _height = MutableLiveData<String?>(null)
    private val _diameter = MutableLiveData<String?>(null)
    private val _season = MutableLiveData<Season?>(null)
    private val _brand = MutableLiveData<Brand?>(null)
    private val _location = MutableLiveData<Location?>(null)
    private val _quantity = MutableLiveData<Int?>(null)

    val Type: LiveData<TireType?> = _type
    val Quantity: LiveData<Int?> = _quantity
    val Season: LiveData<Season?> = _season
    val Brand: LiveData<Brand?> = _brand
    val Location: LiveData<Location?> = _location
    val Width: LiveData<String?> = _width
    val Height: LiveData<String?> = _height
    val Diameter: LiveData<String?> = _diameter

    fun setType(value: TireType?) {_type.value = value}
    fun setWidth(value: String?) { _width.value = value }
    fun setHeight(value: String?) { _height.value = value }
    fun setDiameter(value: String?) { _diameter.value = value }
    fun setSeason(value: Season?) { _season.value = value }
    fun setBrand(value: Brand?) { _brand.value = value }
    fun setLocation(value: Location?) { _location.value = value }
    fun setQuantity(value: Int?) {_quantity.value = value}

    fun start(){
        repository.start()
    }
    fun getFilteredTires(type: String?): LiveData<List<Tire>> {
        return MediatorLiveData<List<Tire>>().apply {

            fun update() {
                val list = tires.value ?: emptyList()
                val w = _width.value?.toFloatOrNull()
                val h = _height.value?.toFloatOrNull()
                val d = _diameter.value?.toFloatOrNull()

                value = list.filter { tire ->
                    (type == null || tire.type == type) &&
                            (w == null || tire.width == w) &&
                            (h == null || tire.height == h) &&
                            (d == null || tire.diameter == d)
                }
                    .sortedWith(
                        compareBy<Tire>(
                            { it.diameter },
                            { it.width },
                            { it.height }
                        )
                    )

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

    fun updateQuantity(tireId: String , delta:Int){
        if (delta == 0) return
        repository.updateTireQuantity(tireId, delta)
    }

    private fun findMatchingTire(tires: List<Tire>): Tire? {
        return tires.firstOrNull {
            val width = _width.value?.toFloatOrNull()
            val height = _height.value?.toFloatOrNull() ?: 0f
            val diameter = _diameter.value?.toFloatOrNull()

            it.type == _type.value?.name &&
                    it.width == width &&
                    it.height == height &&
                    it.diameter == diameter &&
                    it.season == _season.value?.name &&
                    it.brand == _brand.value?.name &&
                    it.location == _location.value?.name
        }
    }

    fun addOrIncreaseTire() {
        val tire = buildTireFromState() ?: return
        val current = tires.value ?: return

        val existing = findMatchingTire(current)

        if (existing != null) {
            repository.updateTireQuantity(existing.id, tire.quantity)
        } else {
            repository.addTire(tire)
        }
        cleanSetValues()
    }

    fun removeTire() {
        val tire = buildTireFromState() ?: return
        val current = tires.value ?: return

        val existing = findMatchingTire(current) ?: return

        repository.updateTireQuantity(existing.id, -tire.quantity)

        cleanSetValues()
    }
    private fun buildTireFromState(): Tire? {
        val type = _type.value ?: return null
        val width = _width.value?.toFloatOrNull() ?: return null
        val height = _height.value?.toFloatOrNull() ?: 0f //tractor case
        val diameter = _diameter.value?.toFloatOrNull() ?: return null
        val season = _season.value ?: return null
        val brand = _brand.value ?: return null
        val location = _location.value ?: return null
        val quantity = _quantity.value ?: return null

        return Tire(
            type = type.name,
            width = width,
            height = height,
            diameter = diameter,
            season = season.name,
            brand = brand.name,
            location = location.name,
            quantity = quantity
        )
    }

    fun cleanSetValues(){
        _width.value = null
        _height.value = null
        _diameter.value = null
        _season.value = null
        _type.value = null
        _brand.value = null
        _location.value = null
        _quantity.value = null
    }
}