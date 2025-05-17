package com.example.healthapp.Doctor.viewmodel

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.healthapp.Doctor.model.Doctor

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject



class DoctorViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    var doctorList by mutableStateOf<List<Doctor>>(emptyList())
        private set

    var loading by mutableStateOf(false)
        private set

    fun addDoctor(doctor: Doctor, imageUri: Uri?, onSuccess: () -> Unit, onError: (String) -> Unit) {
        loading = true

        if (imageUri != null) {
            val fileName = UUID.randomUUID().toString()
            val imageRef = storage.reference.child("doctors/$fileName")

            imageRef.putFile(imageUri)
                .continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let { throw it }
                    }
                    imageRef.downloadUrl
                }
                .addOnSuccessListener { uri ->
                    val doctorWithImage = doctor.copy(imageUrl = uri.toString())

                    db.collection("doctors")
                        .add(doctorWithImage)
                        .addOnSuccessListener {
                            loading = false
                            onSuccess()
                        }
                        .addOnFailureListener {
                            loading = false
                            onError(it.message ?: "Failed to add doctor")
                        }
                }
                .addOnFailureListener {
                    loading = false
                    onError(it.message ?: "Image upload failed")
                }
        } else {
            db.collection("doctors")
                .add(doctor)
                .addOnSuccessListener {
                    loading = false
                    onSuccess()
                }
                .addOnFailureListener {
                    loading = false
                    onError(it.message ?: "Failed to add doctor")
                }
        }
    }

    fun fetchDoctors() {
        loading = true
        db.collection("doctors")
            .get()
            .addOnSuccessListener { result ->
                doctorList = result.documents.map { doc ->
                    doc.toObject(Doctor::class.java)?.copy(id = doc.id) ?: Doctor()
                }
                loading = false
            }
            .addOnFailureListener {
                loading = false
            }
    }
}





