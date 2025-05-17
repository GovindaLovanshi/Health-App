package com.example.healthapp.appointment.viewmodel

import androidx.lifecycle.ViewModel
import com.example.healthapp.appointment.model.Appointment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppointmentViewModel: ViewModel() {


    private val db = FirebaseFirestore.getInstance()

    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> = _appointments

    fun addAppointment(appointment: Appointment) {
        val docRef = db.collection("appointments").document()
        val dataWithId = appointment.copy(id = docRef.id)
        docRef.set(dataWithId)
    }

    fun fetchAppointments() {
        db.collection("appointments")
            .addSnapshotListener { snapshot, error ->
                if (error != null || snapshot == null) return@addSnapshotListener
                val list = snapshot.documents.mapNotNull { it.toObject(Appointment::class.java) }
                _appointments.value = list
            }
    }
}