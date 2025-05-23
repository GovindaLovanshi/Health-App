package com.example.healthapp.labtest.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthapp.Naviagtion.Routes
import com.example.healthapp.R
import com.example.healthapp.Screens.Search
import com.example.healthapp.appointment.form.AppointmentFormActivity
import com.example.healthapp.appointment.view.Item
import com.example.healthapp.appointment.viewmodel.AppointmentViewModel
import com.example.healthapp.labtest.model.LabTest
import com.example.healthapp.labtest.viewmodel.LabTestViewModel


@Preview
@Composable
fun LabTestScreen(){

    val viewModel : LabTestViewModel = viewModel  ()
    val labTests by viewModel.appointments.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(true) {
        viewModel.fetchTest()
    }

    Scaffold (
        topBar = {

            TopBarLabTest()

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val intent = Intent(context, LabTestActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.size(65.dp),
                contentColor = Color.White,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }
        },

    ){ padding->
        LazyColumn(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(padding)
                .background(color = colorResource(R.color.lightBlue)),
            horizontalAlignment = Alignment.CenterHorizontally

        ){

            item {
                Search()

            }

            item {
                Row(modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)) {

                    Text(
                        text = "Recommended Packages", color = colorResource(id = R.color.darkGreen),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "View all",
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.darkGreen),
                        fontSize = 16.sp
                    )
                }

            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }



            items(labTests) { lab ->
                Item(
                    Test = lab,
                    onClick = {

                    })
            }
        }
    }

}


@Composable
fun Item(Test: LabTest,
         onClick :() -> Unit) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .shadow(elevation = 4.dp)
            .background(color = colorResource(id = R.color.lightBlue))
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),


        ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = Test.facility,
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 2
                )

                Text(
                    text = "Includes ${Test.testNo} tests",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.location),
                        contentDescription = null
                    )
                    Text(
                        text = Test.address,
                        color = Color.Black,
                        fontSize = 12.sp,
                        maxLines = 1,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }



            }

            Row(verticalAlignment = Alignment.CenterVertically) {

                Row {

                    Button(
                        modifier = Modifier.width(150.dp),
                        onClick = {
                            val phoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${Test.mobile}"))
                            context.startActivity(phoneIntent)
                        },

                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkGreen))
                    ) {
                        Text(
                            text = "Book", color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }

        }



        HorizontalDivider()

        Spacer(modifier = Modifier.height(25.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
//            Image(
//                painter = painterResource(R.drawable.homed),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(60.dp).padding(start = 12.dp)
//                    .clip(CircleShape),
//
//                )

            Spacer(modifier = Modifier.width(15.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.btn_2),
                        contentDescription = null
                    )
                    Text(
                        text = "Fasting Required",
                        color = Color.Black,
                        fontSize = 12.sp,
                        maxLines = 1,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.btn_2),
                        contentDescription = null
                    )
                    Text(
                        text = "Report in ${Test.time} Hrs",
                        color = Color.Black,
                        fontSize = 12.sp,
                        maxLines = 1,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

            }

        }


    }

    HorizontalDivider()

}


@Preview
@Composable
fun TopBarLabTest (){

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
                text = " Body Test List",
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
