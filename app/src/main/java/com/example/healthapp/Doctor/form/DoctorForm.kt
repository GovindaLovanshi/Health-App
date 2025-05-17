package com.example.healthapp.Doctor.form

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.healthapp.Doctor.viewmodel.DoctorViewModel
import com.example.healthapp.R
import com.example.healthapp.blood.form.TopBarBloodForm
import android.net.Uri
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.healthapp.Doctor.model.Doctor
import com.example.healthapp.Naviagtion.Routes


@Composable
fun DoctorForm(navHostController: NavHostController) {
    val viewModel : DoctorViewModel = viewModel()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()


    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var designation by remember { mutableStateOf("") }
    var about by remember { mutableStateOf("") }
    var imageUri by remember {  mutableStateOf<Uri?>(null) }


    // Image picker
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }



    Scaffold(

        topBar = {


            TopBarDoctorForm()

        },
        bottomBar = {


            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val doctor = Doctor(
                        name = name,
                        date = date,
                        mobile = mobile,
                        address = address,
                        designation = designation,
                        description = about,
                    )

                    viewModel.addDoctor(doctor, imageUri,
                        onSuccess = {
                            Toast.makeText(context, "Doctor added successfully", Toast.LENGTH_SHORT).show()
                            navHostController.popBackStack()
                        },
                        onError = {
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        }
                    )
                },

                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkGreen))
            ) {
                Text(
                    text = "Add", color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

        }
    ) { padding ->

        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.lightBlue)),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            // Image Picker

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { launcher.launch("image/*") }
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(imageUri),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Text("Tap to select image")
                    }
                }


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(android.graphics.Color.parseColor("#EEEEFB"))),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {


                    Spacer(modifier = Modifier.padding(20.dp))



                    Spacer(modifier = Modifier.padding(20.dp))


                    TextField(
                        modifier = Modifier.width(350.dp),
                        value = name,
                        onValueChange = { name = it },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
                            unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
                        ),
                        placeholder = { Text(text = "Name Of Donater") }

                    )

                    Spacer(modifier = Modifier.padding(20.dp))


                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {


                        TextField(
                            modifier = Modifier.width(170.dp),
                            value = date,


                            onValueChange = { date = it },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
                                unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
                            ),
                            placeholder = { Text(text = "Enter Date") }

                        )

                        Spacer(modifier = Modifier.padding())

                        TextField(
                            modifier = Modifier.width(170.dp),
                            value = designation,
                            onValueChange = { designation = it },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
                                unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
                            ),
                            placeholder = { Text(text = "Designation") }

                        )


                    }


                    Spacer(modifier = Modifier.padding(20.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {


                        TextField(
                            modifier = Modifier.width(170.dp),
                            value = address,
                            onValueChange = { address = it },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
                                unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
                            ),
                            placeholder = { Text(text = "Address") }

                        )

                        Spacer(modifier = Modifier.padding())

                        TextField(
                            modifier = Modifier.width(170.dp),
                            value = mobile,
                            onValueChange = { mobile = it },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
                                unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
                            ),
                            placeholder = { Text(text = "Mobile Number") }

                        )


                    }

                    Spacer(modifier = Modifier.padding(20.dp))



                    TextField(
                        modifier = Modifier.width(350.dp).height(200.dp),
                        value = about,
                        onValueChange = { about = it },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
                            unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
                        ),
                        placeholder = { Text(text = "About Of Doctor") }

                    )

                    Spacer(modifier = Modifier.padding(10.dp))


                }

            }
        }

    }

}

@Composable
fun TopBarDoctorForm() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(color = colorResource(R.color.darkGreen))
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .clickable { })

            Text(
                text = " Doctor Form",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )

        }
    }
}