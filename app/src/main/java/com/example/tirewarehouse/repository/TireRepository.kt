package com.example.tirewarehouse.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tirewarehouse.data.Tire
import com.google.firebase.firestore.FirebaseFirestore


class TireRepository{
    private val db = FirebaseFirestore.getInstance()
    private val tiresRef = db.collection("tires")

    var _tires = MutableLiveData<List<Tire>>()
    val tires: LiveData<List<Tire>> = _tires

    init {
        fetchDatabaseData()
    }

    private fun fetchDatabaseData(){
        tiresRef.addSnapshotListener { snapshot, error ->
            if ( error != null) return@addSnapshotListener

            val list = snapshot?.toObjects(Tire::class.java) ?: emptyList()
            list.forEachIndexed { index,tire ->
                snapshot?.documents?.get(index)?.let { tire.id = it.id }
            }
            _tires.value = list
        }

    }

    fun addTire(tire: Tire){
        val doc = tiresRef.document()
        doc.set(tire.copy(id = doc.id))
    }

    fun updateTire(tire: Tire){
        tiresRef.document(tire.id).set(tire)
    }

    fun removeTire(tireId: String, amount: Int){
        val doc = tiresRef.document(tireId)

        doc.get().addOnSuccessListener { snapshot ->
            val tire = snapshot.toObject(Tire::class.java)
            if(tire != null){
                var newQuantity = tire.quantity - amount
                if(newQuantity < 0){
                    newQuantity = 0
                }
                doc.update("quantity",newQuantity)
            }
        }
    }
}