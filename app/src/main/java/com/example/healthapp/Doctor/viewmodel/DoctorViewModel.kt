package com.example.healthapp.Doctor.viewmodel

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import com.example.healthapp.Doctor.model.DoctorDetails
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



class DoctorViewModel : ViewModel() {


        private val firestore = FirebaseFirestore.getInstance()
        private val storage = FirebaseStorage.getInstance()

    private val _formState = mutableStateOf<List<DoctorDetails>>(emptyList())
    val formState: State<List<DoctorDetails>> = _formState

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    fun submitForm(
        name: String,
        designation: String,
        address: String,
        mobile: String,
        description: String,
        date: String,
        imageUri: Uri?,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        _loading.value = true
        val docRef = firestore.collection("forms").document()
        val id = docRef.id

        if (imageUri != null) {
            val imageRef = storage.reference.child("form_images/$id.jpg")
            imageRef.putFile(imageUri)
                .continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let { throw it }
                    }
                    imageRef.downloadUrl
                }.addOnSuccessListener { uri ->
                    val form =DoctorDetails(id, name, designation, mobile, address, description, date, uri.toString())
                    docRef.set(form)
                        .addOnSuccessListener {
                            _loading.value = false
                            fetchForms()
                            onSuccess()
                        }
                        .addOnFailureListener {
                            _loading.value = false
                            onFailure(it)
                        }
                }.addOnFailureListener {
                    _loading.value = false
                    onFailure(it)
                }
        } else {
            val form = DoctorDetails(id, name, designation, mobile, address, description, date, "")
            docRef.set(form)
                .addOnSuccessListener {
                    _loading.value = false
                    fetchForms()
                    onSuccess()
                }
                .addOnFailureListener {
                    _loading.value = false
                    onFailure(it)
                }
        }
    }

    fun fetchForms() {
        firestore.collection("forms")
            .get()
            .addOnSuccessListener { result ->
                _formState.value = result.map { it.toObject(DoctorDetails::class.java) }
            }
    }

    fun deleteForm(formId: String, imageUrl: String?, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection("forms").document(formId)
            .delete()
            .addOnSuccessListener {
                if (!imageUrl.isNullOrEmpty()) {
                    val ref = storage.getReferenceFromUrl(imageUrl)
                    ref.delete()
                        .addOnSuccessListener {
                            fetchForms()
                            onSuccess()
                        }
                        .addOnFailureListener(onFailure)
                } else {
                    fetchForms()
                    onSuccess()
                }
            }
            .addOnFailureListener(onFailure)
    }
}

//class DoctorViewModel:ViewModel() {
//
//
//        private val firestore = FirebaseFirestore.getInstance()
//        private val storage = FirebaseStorage.getInstance()
//        private val _formState = mutableStateOf<List<DoctorDetails>>(emptyList())
//        val formState: State<List<DoctorDetails>> = _formState
//
//        private val _loading = mutableStateOf(false)
//        val loading: State<Boolean> = _loading
//
//        fun submitForm(
//            name: String,
//            designation: String,
//            address: String,
//            mobile: String,
//            description: String,
//            date: String,
//            imageUri: Uri?,
//            onSuccess: () -> Unit,
//            onFailure: (Exception) -> Unit
//        ) {
//            _loading.value = true
//
//            val docRef = firestore.collection("forms").document()
//            val id = docRef.id
//
//            if (imageUri != null) {
//                val imageRef = storage.reference.child("form_images/$id.jpg")
//                imageRef.putFile(imageUri)
//                    .continueWithTask { task ->
//                        if (!task.isSuccessful) {
//                            task.exception?.let { throw it }
//                        }
//                        imageRef.downloadUrl
//                    }.addOnSuccessListener { uri ->
//                        val form = DoctorDetails(id, name, designation, mobile, address, description, date, uri.toString())
//                        docRef.set(form)
//                            .addOnSuccessListener {
//                                _loading.value = false
//                                fetchForms()
//                                onSuccess()
//                            }
//                            .addOnFailureListener {
//                                _loading.value = false
//                                onFailure(it)
//                            }
//                    }.addOnFailureListener {
//                        _loading.value = false
//                        onFailure(it)
//                    }
//            } else {
//                val form = DoctorDetails(id, name, designation, mobile, address, description, date, "")
//                docRef.set(form)
//                    .addOnSuccessListener {
//                        _loading.value = false
//                        fetchForms()
//                        onSuccess()
//                    }
//                    .addOnFailureListener {
//                        _loading.value = false
//                        onFailure(it)
//                    }
//            }
//        }
//
//        fun fetchForms() {
//            firestore.collection("forms")
//                .get()
//                .addOnSuccessListener { result ->
//                    _formState.value = result.map { it.toObject(DoctorDetails::class.java) }
//                }
//        }
//
//        fun deleteForm(formId: String, imageUrl: String?, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
//            firestore.collection("forms").document(formId)
//                .delete()
//                .addOnSuccessListener {
//                    if (!imageUrl.isNullOrEmpty()) {
//                        val ref = storage.getReferenceFromUrl(imageUrl)
//                        ref.delete()
//                            .addOnSuccessListener {
//                                fetchForms()
//                                onSuccess()
//                            }
//                            .addOnFailureListener(onFailure)
//                    } else {
//                        fetchForms()
//                        onSuccess()
//                    }
//                }
//                .addOnFailureListener(onFailure)
//        }
//    }



