package com.example.healthapp.blood.form

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthapp.Naviagtion.Routes
import com.example.healthapp.R
import com.example.healthapp.blood.view.TopBarBlood

@Preview
@Composable
fun BloodForm() {

    Scaffold(

        topBar = {


            TopBarBloodForm()

        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.lightBlue)),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            var name by remember { mutableStateOf("") }
            var DOB by remember { mutableStateOf("") }
            var mobile by remember { mutableStateOf("") }
            var address by remember { mutableStateOf("") }
            var blood by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(android.graphics.Color.parseColor("#EEEEFB"))),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {



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
                        value = DOB,


                        onValueChange = { DOB = it },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
                            unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
                        ),
                        placeholder = { Text(text = "Date Of Birth") }

                    )

                    Spacer(modifier = Modifier.padding())

                    TextField(
                        modifier = Modifier.width(170.dp),
                        value = blood,
                        onValueChange = { blood = it },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
                            unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
                        ),
                        placeholder = { Text(text = "Blood Group") }

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

                Spacer(modifier = Modifier.padding(120.dp))

                Button(
                    modifier = Modifier.width(250.dp),
                    onClick = { /* TODO: Implement click action */ },
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
fun TopBarBloodForm() {

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
                text = " Blood Form",
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
