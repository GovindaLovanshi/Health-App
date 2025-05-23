package com.example.healthapp.appointment.model

import kotlinx.serialization.Serializable

data class Appointment(
    val name: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val address: String = "",
    val mobile: String = "",
    val injury: String = "",
    val date: String = "",
    val id: String = "" // Firestore document ID
)
