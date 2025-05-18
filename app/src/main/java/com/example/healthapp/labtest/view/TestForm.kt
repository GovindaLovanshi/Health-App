package com.example.healthapp.labtest.view

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
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.NavHostController
import com.example.healthapp.Naviagtion.Routes
import com.example.healthapp.appointment.model.Appointment
import com.example.healthapp.appointment.viewmodel.AppointmentViewModel
import com.example.healthapp.labtest.model.LabTest
import com.example.healthapp.labtest.viewmodel.LabTestViewModel
import com.example.healthapp.ui.theme.HealthAppTheme


class LabTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestForm()
        }
    }
}
@Preview
@Composable
fun TestForm() {

    val context = LocalContext.current
    val viewModel: LabTestViewModel = viewModel()


    var facility by remember { mutableStateOf("") }
    var testNo by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }



    Scaffold(

        topBar = {


            TopBarAppointmentForm()

        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.lightBlue)),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {



            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(android.graphics.Color.parseColor("#EEEEFB"))),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {




                Spacer(modifier = Modifier.padding(20.dp))


                TextField(
                    modifier = Modifier.width(350.dp),
                    value = facility,
                    onValueChange = { facility = it },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
                        unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
                    ),
                    placeholder = { Text(text = "Test Details") }

                )

                Spacer(modifier = Modifier.padding(20.dp))


                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {


                    TextField(
                        modifier = Modifier.width(170.dp),
                        value = testNo,


                        onValueChange = { testNo = it },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
                            unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
                        ),
                        placeholder = { Text(text = "Number Of test") }

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
                        placeholder = { Text(text = "mobile Number") }

                    )


                }
//            Spacer(modifier = Modifier.padding(20.dp))

//            Row (horizontalArrangement = Arrangement.spacedBy(8.dp)){
//
//
//                TextField(
//                    modifier = Modifier.width(170.dp),
//                    value = text,
//                    onValueChange = { text = it },
//                    placeholder = { Text(text = "Name DR") }
//
//                )
//
//                Spacer(modifier = Modifier.padding())
//
//                TextField(
//                    modifier = Modifier.width(170.dp),
//                    value = text,
//                    onValueChange = { text = it },
//                    placeholder = { Text(text = "Name DR") }
//
//                )
//
//
//
//            }

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
                        value = time,
                        onValueChange = { time = it },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
                            unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
                        ),
                        placeholder = { Text(text = "time in Hour") }

                    )


                }

                Spacer(modifier = Modifier.padding(20.dp))








                Spacer(modifier = Modifier.padding(10.dp))

                Button(
                    modifier = Modifier.width(250.dp),
                    onClick = {
                        if (facility.isNotBlank() && mobile.isNotBlank() && time.isNotBlank() && address.isNotBlank() &&testNo.isNotBlank()) {
                            val test = LabTest(
                                facility = facility,
                                testNo = testNo,
                                address = address,
                                mobile = mobile,
                                time = time
                            )
                            viewModel.addAppointment(test)
                            Toast.makeText(context, "LabTest Added", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Please fill all required fields", Toast.LENGTH_SHORT).show()
                        }
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

        }

    }

}

@Composable
fun TopBarAppointmentForm() {

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


            Text(
                text = " Appointment Form",
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