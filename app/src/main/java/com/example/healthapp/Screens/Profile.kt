package com.example.healthapp.Screens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.healthapp.Authentication.ViewModel.MobileAuthViewModel
import com.example.healthapp.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import okhttp3.Route


@Composable
fun Profile( ){

    val phoneAuthViewModel: MobileAuthViewModel = viewModel()
    var name by remember {
        mutableStateOf("")
    }

    var profileImage by remember {
        mutableStateOf<Uri?>(null)
    }

    var bitmapImage by remember {
        mutableStateOf<Bitmap?>(null)
    }

    val firebaseAuth = Firebase.auth

    val phoneNumber = firebaseAuth.currentUser?.phoneNumber?:""
    val userId = firebaseAuth.currentUser?.uid?:""
    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->

            profileImage = uri

            uri?.let {

                bitmapImage = if (Build.VERSION.SDK_INT < 28) {
                    @Suppress("DEPRECATION")
                    android.provider.MediaStore.Images.Media.getBitmap(context.contentResolver, it)

                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    ImageDecoder.decodeBitmap(source)
                }
            }
        }
    )

    Column (
        modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ){


        Box(
            modifier = Modifier.size(128.dp)
                .clip(CircleShape)
                .border(2.dp,color = androidx.compose.ui.graphics.Color.Gray, shape = CircleShape )
                .clickable { imagePickerLauncher.launch("image/*") }
        ){
            if(bitmapImage != null){

                Image(
                    bitmap = bitmapImage!!.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                        .clip(CircleShape)
                    , contentScale = ContentScale.Crop
                )
            }
            else if(profileImage != null){

                Image(painter = rememberImagePainter(profileImage),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                        .align(Alignment.Center))

            }

            else{

                Image(painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                        .align(Alignment.Center))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "$phoneNumber")

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            onValueChange = {name = it},
            value = name,
            label = {
                Text("Name")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = androidx.compose.ui.graphics.Color.Transparent,
                focusedContainerColor= androidx.compose.ui.graphics.Color.Transparent,

                unfocusedIndicatorColor = colorResource(id = R.color.teal_700),
                focusedIndicatorColor = colorResource(id = R.color.teal_700)
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
//            navHostController.navigate(Routes.HomeScreen)
        },
            colors = ButtonDefaults.buttonColors(colorResource(R.color.teal_700))
        ) {

            Text("Save")
        }

    }
}