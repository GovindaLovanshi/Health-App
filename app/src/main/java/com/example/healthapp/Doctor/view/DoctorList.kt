package com.example.healthapp.Doctor.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.healthapp.Naviagtion.Routes
import com.example.healthapp.R


@Composable
fun DoctorList(navHostController: NavHostController){



    Scaffold(
        floatingActionButton = {

            FloatingActionButton(
                onClick = {

                    navHostController.navigate(Routes.DoctorForm)

                },
                modifier = Modifier.size(65.dp),
                contentColor = Color.White,
            ) {

                Icon(painter = painterResource(id = R.drawable.btn_1),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp))
            }
        }
    ) {padding ->


        LazyColumn (modifier = Modifier.padding(padding)){

            item {
                ItemList()
            }
        }



    }
    }

@Preview
@Composable
fun ItemList() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),

        ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.cartoon),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Doctor Name",
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
                        text = "Clinic Address",
                        color = Color.Black,
                        fontSize = 12.sp,
                        maxLines = 1,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

            }
//            Column(
//                modifier = Modifier.weight(1f),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                Text(
//                    text = "Quantity",
//                    color = Color.Black,
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Bold,
//                    maxLines = 1
//                )
//                Row(verticalAlignment = Alignment.CenterVertically) {
//
//                    Text(
//                        text = "Rate",
//                        color = Color.Black,
//                        fontSize = 12.sp,
//                        maxLines = 1,
//                        modifier = Modifier.padding(start = 4.dp)
//                    )
//                }
//
//            }
        }

        HorizontalDivider()

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(R.drawable.homed),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp).
                    padding(start = 12.dp)
                    .clip(CircleShape),

                )

            Spacer(modifier = Modifier.width(15.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Seller Name",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = "Address",
                        color = Color.Black,
                        fontSize = 12.sp,
                        maxLines = 1,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

            }
            Row{

                Image(painter = painterResource(R.drawable.bluecall),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)

                )
            }
        }




    }

}

