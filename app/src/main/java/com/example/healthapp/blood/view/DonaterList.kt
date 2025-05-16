package com.example.healthapp.blood.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.healthapp.Naviagtion.Routes
import com.example.healthapp.R
import com.example.healthapp.blood.viewmodel.BloodDonaterViewModel
import okhttp3.Route


@Composable
fun DonaterList(navHostController: NavHostController){

    val BlodViewModel : BloodDonaterViewModel = viewModel()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        BlodViewModel.fetchUserData()
    }
    Scaffold(
        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Routes.BloodForm)

                },
                modifier = Modifier.size(65.dp),
                contentColor = Color.White,
            ) {

                Icon(painter = painterResource(id = R.drawable.btn_1),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp))
            }
        },
        topBar = {
           TopBarBlood()
        }
    ) {padding->





        LazyColumn(modifier = Modifier.padding(padding)) {

           item{
               ItemList()
               ItemList()

           }
        }
    }

}

@Preview
@Composable
fun ItemList() {

    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .shadow(elevation = 4.dp)
            .background(color = colorResource(id = R.color.lightBlue))
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),


        ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
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
                    text = "Gaju Singh",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Text(
                    text = "12 December 2000",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.location),
                        contentDescription = null
                    )
                    Text(
                        text = "Indore",
                        color = Color.Black,
                        fontSize = 12.sp,
                        maxLines = 1,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

            }

        }

        HorizontalDivider()

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
                Text(
                    text = "Blood Group  Name",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = "+91 12345678",
                        color = Color.Black,
                        fontSize = 12.sp,
                        maxLines = 1,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

            }
            Row {

                Image(
                    painter = painterResource(R.drawable.bluecall),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)

                )
            }
        }


    }

    HorizontalDivider()

}
//@Preview
//@Composable
//fun Donate(
//
//) {
//    val BlodViewModel : BloodDonaterViewModel = viewModel()
//    val context = LocalContext.current
//    LaunchedEffect(Unit) {
//        BlodViewModel.fetchUserData()
//    }
//
//    Card(
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxWidth()
//            .clickable {
//
//            },
//        shape = RoundedCornerShape(12.dp),
//        elevation = 6.dp
//
//    ) {
//        Row(
//            modifier = Modifier.padding(12.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//
//            Image(
//                painter = painterResource(R.drawable.cartoon),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(80.dp)
//                    .clip(CircleShape),
//                contentScale = ContentScale.Crop
//            )
//
//            Spacer(modifier = Modifier.width(12.dp)) // Changed from height to width (for horizontal spacing)
//
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                verticalArrangement = Arrangement.spacedBy(6.dp)
//            ) {
//
//                Text(
//                    text = "Name",
//                    color = Color.Black,
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Bold,
//                    maxLines = 1
//                )
//
//                Spacer(modifier = Modifier.width(12.dp))
//
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Image(
//                        painter = painterResource(R.drawable.location),
//                        contentDescription = null,
//                        modifier = Modifier.size(16.dp)
//                    )
//                    Text(
//                        text = "Address",
//                        color = Color.Black,
//                        fontSize = 12.sp,
//                        maxLines = 1,
//                        modifier = Modifier.padding(start = 4.dp)
//                    )
//                }
//
//
//            }
//        }
//    }
//}


@Preview
@Composable
fun TopBarBlood (){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(color = colorResource(R.color.teal_700))
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Text(
                text = " Blood Details",
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