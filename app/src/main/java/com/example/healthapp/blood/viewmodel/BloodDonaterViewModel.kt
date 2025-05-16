package com.example.healthapp.blood.viewmodel


import androidx.lifecycle.ViewModel



import android.net.Uri
import androidx.lifecycle.viewModelScope

import com.android.identity.util.UUID
import com.example.healthapp.blood.model.BloodDetails

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BloodDonaterViewModel :ViewModel(){





        private val db = FirebaseFirestore.getInstance()
        private val storage = FirebaseStorage.getInstance()
        private val _donors = MutableStateFlow<List<BloodDetails>>(emptyList())
        val donors: StateFlow<List<BloodDetails>> = _donors

        // 🔹 Add/Submit data
        fun submitDonorData(
            name: String,
            dob: String,
            blood: String,
            address: String,
            mobile: String,
            imageUri: Uri?,
            onSuccess: () -> Unit,
            onFailure: (Exception) -> Unit
        ) {
            viewModelScope.launch {
                if (imageUri != null) {
                    val imageRef = storage.reference.child("blood_donors/${UUID.randomUUID()}.jpg")
                    imageRef.putFile(imageUri)
                        .continueWithTask { task ->
                            if (!task.isSuccessful) {
                                task.exception?.let { throw it }
                            }
                            imageRef.downloadUrl
                        }
                        .addOnSuccessListener { uri ->
                            val donor = BloodDetails(
                                id = UUID.randomUUID().toString(),
                                name = name,
                                dob = dob,
                                blood = blood,
                                address = address,
                                mobile = mobile,
                                image = uri.toString()
                            )
                            db.collection("blood_donors")
                                .document(donor.id)
                                .set(donor)
                                .addOnSuccessListener { onSuccess() }
                                .addOnFailureListener { e -> onFailure(e) }
                        }
                        .addOnFailureListener { e -> onFailure(e) }
                } else {
                    onFailure(Exception("Image not selected"))
                }
            }
        }

        // 🔹 Fetch all donors
        fun fetchDonors() {
            db.collection("blood_donors")
                .get()
                .addOnSuccessListener { result ->
                    val donorsList = result.mapNotNull { it.toObject(BloodDetails::class.java) }
                    _donors.value = donorsList
                }
        }

        // 🔹 Delete donor
        fun deleteDonor(id: String, imageUrl: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
            val imageRef = storage.getReferenceFromUrl(imageUrl)

            imageRef.delete().addOnSuccessListener {

                db.collection("blood_donors")
                    .document(id)
                    .delete()
                    .addOnSuccessListener {
                        _donors.value = _donors.value.filterNot { it.id == id }
                        onSuccess()
                    }
                    .addOnFailureListener { onFailure(it) }
            }.addOnFailureListener { onFailure(it) }
        }
    }



