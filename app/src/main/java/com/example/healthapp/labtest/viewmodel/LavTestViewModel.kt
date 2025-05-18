package com.example.healthapp.labtest.viewmodel

import androidx.lifecycle.ViewModel
import com.example.healthapp.appointment.model.Appointment
import com.example.healthapp.labtest.model.LabTest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LabTestViewModel: ViewModel() {


    private val db = FirebaseFirestore.getInstance()

    private val _lab = MutableStateFlow<List<LabTest>>(emptyList())
    val appointments: StateFlow<List<LabTest>> = _lab

    fun addAppointment(labTest: LabTest) {
        val docRef = db.collection("lab").document()
        val dataWithId = labTest.copy(id = docRef.id)
        docRef.set(dataWithId)
    }

    fun fetchTest() {
        db.collection("lab")
            .addSnapshotListener { snapshot, error ->
                if (error != null || snapshot == null) return@addSnapshotListener
                val list = snapshot.documents.mapNotNull { it.toObject(LabTest::class.java) }
                _lab.value = list
            }
    }
}