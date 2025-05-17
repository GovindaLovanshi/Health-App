package com.example.healthapp.blood.viewmodel


import androidx.lifecycle.ViewModel



import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope

import com.android.identity.util.UUID
import com.example.healthapp.Doctor.model.Doctor
import com.example.healthapp.blood.model.BloodDetails

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BloodDonaterViewModel :ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    var donorList by mutableStateOf<List<BloodDetails>>(emptyList())
        private set

    var loading by mutableStateOf(false)
        private set

    fun addDonor(donor: BloodDetails, imageUri: Uri?, onSuccess: () -> Unit, onError: (String) -> Unit) {
        loading = true

        if (imageUri != null) {
            val fileName = java.util.UUID.randomUUID().toString()
            val imageRef = storage.reference.child("donors/$fileName")

            imageRef.putFile(imageUri)
                .continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let { throw it }
                    }
                    imageRef.downloadUrl
                }
                .addOnSuccessListener { uri ->
                    val doctorWithImage = donor.copy(image = uri.toString())

                    db.collection("donors")
                        .add(doctorWithImage)
                        .addOnSuccessListener {
                            loading = false
                            onSuccess()
                        }
                        .addOnFailureListener {
                            loading = false
                            onError(it.message ?: "Failed to add donor")
                        }
                }
                .addOnFailureListener {
                    loading = false
                    onError(it.message ?: "Image upload failed")
                }
        } else {
            db.collection("donors")
                .add(donor)
                .addOnSuccessListener {
                    loading = false
                    onSuccess()
                }
                .addOnFailureListener {
                    loading = false
                    onError(it.message ?: "Failed to add donor")
                }
        }
    }

    fun fetchDonors() {
        loading = true
        db.collection("donors")
            .get()
            .addOnSuccessListener { result ->
                donorList = result.documents.map { doc ->
                    doc.toObject(BloodDetails::class.java)?.copy(id = doc.id) ?: BloodDetails()
                }
                loading = false
            }
            .addOnFailureListener {
                loading = false
            }
    }

}



