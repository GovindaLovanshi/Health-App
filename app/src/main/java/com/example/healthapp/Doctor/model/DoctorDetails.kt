package com.example.healthapp.Doctor.model

import java.io.Serializable

data class Doctor(
    val name: String = "",
    val date: String = "",
    val mobile: String = "",
    val address: String = "",
    val designation: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val id: String = "" // Firestore document ID
): Serializable



