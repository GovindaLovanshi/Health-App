package com.example.healthapp.labtest.model

data class LabTest(
    val facility: String = "",
    val testNo: String = "",
    val mobile: String = "",
    val address: String = "",
    val time: String = "",
    val id: String = "" // Firestore document ID
)
