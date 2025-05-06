package com.example.healthapp.Authentication.ViewModel

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthapp.Authentication.PhoneAuthUser
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit




class MobileAuthViewModel :ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    private  val database = FirebaseDatabase.getInstance()

    private  val _authState = MutableStateFlow<AuthState>(AuthState.Ideal)
     val authState = _authState.asStateFlow()

    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    val firebaseUser: MutableLiveData<FirebaseUser?> = _firebaseUser

    private  val userRef = database.reference.child("users")

    private fun siginWithCredential(credential: PhoneAuthCredential, context: Context) {

        _authState.value = AuthState.Loading

        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener{task ->

                if(task.isSuccessful){

                    val user = firebaseAuth.currentUser
                    val phoneAuthUser = PhoneAuthUser(
                        userId = user?.uid?: "",
                        phoneNumber = user?.phoneNumber?: ""
                    )

                    markeUserAsSignedIn(context)
                    _authState.value = AuthState.Success(phoneAuthUser)

                    fetchUserprofile(user?.uid?:"")
                }else{

                    _authState.value = AuthState.Error(task.exception?.message?:"Sign in Failed")
                }
            }

    }

    fun sendVerificationCode(phoneNumber:String, activity:Activity){
        _authState.value = AuthState.Loading


        val option = object :  PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                Log.d("PhoneAuth","onCodeSent triggered. Verification ID:$id")
                _authState.value = AuthState.CodeSent(verificationId = id)
            }
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                siginWithCredential(credential, context = activity)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
//                Log.e("phoneAuth","Verification Failed: ${exception.message}")
//                _authState.value = AuthState.Error(exception.message ? : "Verification Failed")
            }

        }


        val phoneAuthOptions = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(option)
            .build()


        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
    }

   private fun markeUserAsSignedIn(context: Context){

       val sharedPreference = context.getSharedPreferences("app_prefs",Context.MODE_PRIVATE)
       sharedPreference.edit().putBoolean("isSignedIn",true).apply()
   }

    private fun fetchUserprofile(userId:String){

        val userRef = userRef.child(userId)
        userRef.get().addOnSuccessListener { snapshot->

            if(snapshot.exists()){

                val userProfile = snapshot.getValue(PhoneAuthUser::class.java)
                if(userProfile != null){
                    _authState.value = AuthState.Success(userProfile)
                }
            }
        }.addOnFailureListener{
            _authState.value = AuthState.Error("Failed to fetch user")
        }
    }

    fun verifyCode(otp:String,context: Context){

        val currentAuthState = _authState.value

        if(currentAuthState !is AuthState.CodeSent || currentAuthState.verificationId.isEmpty()){

            Log.e("Phone auth","Attempting to verify OTP without a valid verification ID")

            _authState.value = AuthState.Error("verification not started a invalid Id")

            return

        }

        val credential = PhoneAuthProvider.getCredential(currentAuthState.verificationId,otp)
        siginWithCredential(credential,context)
    }

    fun saveUserprofile(userId:String,name:String,profileImage:Bitmap?){

        val database = FirebaseDatabase.getInstance().reference

        val encodedImage = profileImage?.let { convertBitmapToBase64(it) }
        val userprofile = encodedImage?.let {
            PhoneAuthUser(
                userId = userId,
                name= name,
                phoneNumber = Firebase.auth.currentUser?.phoneNumber?:"",
                profileImage = it,
            )
        }

        database.child("users").child(userId).setValue(userprofile)


    }

    fun convertBitmapToBase64(bitmap:Bitmap):String{

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray,Base64.DEFAULT)

    }

    fun resetAuthState(){

        _authState.value = AuthState.Ideal


    }

    fun logout() {
        firebaseAuth.signOut()
            _firebaseUser.postValue(null)
    }

}

sealed class AuthState{


    object  Ideal:AuthState()
    object Loading:AuthState()
    data class CodeSent(val verificationId:String):AuthState()
    data class Success(val user:PhoneAuthUser):AuthState()
    data class Error(val message:String):AuthState()
}