package com.example.healthapp.blood.viewmodel


import androidx.annotation.OptIn
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi

import android.net.Uri

import com.android.identity.util.UUID
import com.example.healthapp.blood.model.BloodDetails
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class BloodDonaterViewModel :ViewModel(){

    private val dbRef = FirebaseDatabase.getInstance().getReference("bloodForms")
    private val storageRef = FirebaseStorage.getInstance().reference.child("userImages")

    var bloodDetails by mutableStateOf<List<BloodDetails>>(emptyList())
        private set

    var image by mutableStateOf<Uri?>(null)

    fun uploadDataWithImage(
        formData: BloodDetails,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val imageUri = this.image
        if (imageUri == null) {
            onFailure("Image not selected")
            return
        }

        val fileName = UUID.randomUUID().toString() + ".jpg"
        val imageRef = storageRef.child(fileName)

        imageRef.putFile(imageUri).addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                val id = dbRef.push().key ?: UUID.randomUUID().toString()
                val dataWithId = formData.copy(id = id, image = uri.toString())

                dbRef.child(id).setValue(dataWithId)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFailure(it.message ?: "Error saving data") }
            }
        }.addOnFailureListener {
            onFailure(it.message ?: "Image upload failed")
        }
    }

    fun fetchUserData() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tempList = mutableListOf<BloodDetails>()
                for (child in snapshot.children) {
                    child.getValue(BloodDetails::class.java)?.let {
                        tempList.add(it)
                    }
                }
                bloodDetails = tempList
            }

            @OptIn(UnstableApi::class)
            override fun onCancelled(error: DatabaseError) {
                Log.e("FormViewModel", "Database error: ${error.message}")
            }
        })
    }

    fun deleteUserData(id: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        dbRef.child(id).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it.message ?: "Delete failed") }
    }
}