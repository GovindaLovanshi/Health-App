package com.example.healthapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import com.example.healthapp.Naviagtion.Routes
import com.example.healthapp.R


@Composable
fun Setting(navHostController: NavHostController){

    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(android.graphics.Color.parseColor("#f2f1f6"))),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout(
            Modifier
                .height(200.dp)
                .background(color = Color(android.graphics.Color.parseColor("#293A16")))
        ) {
            val (topImg, profile, title, back, pen) = createRefs()

            Image(
                painterResource(id = R.drawable.experience), null, Modifier
                    .fillMaxWidth().height(100.dp)
                    .constrainAs(topImg) {
                        bottom.linkTo(parent.bottom)
                    })
//            Image(
//                painterResource(id = R.drawable.location), null, Modifier
//                    .fillMaxWidth()
//                    .constrainAs(profile) {
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                        bottom.linkTo(topImg.bottom)
//                    })
            Text(text = "Profile",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 30.sp
                ),
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(parent.top, margin = 32.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Image(
                painterResource(id = R.drawable.back), null, Modifier
                    .constrainAs(back) {
                        top.linkTo(parent.top, margin = 24.dp)
                        start.linkTo(parent.start, margin = 24.dp)

                    })
//            Image(
//                painterResource(id = R.drawable.fav_icon), null, Modifier
//                    .constrainAs(pen) {
//                        top.linkTo(profile.top)
//                        start.linkTo(profile.end)
//                    })
        }
        Text(
            text = "Govinda",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp),
            color = Color(android.graphics.Color.parseColor("#293A16"))
        )
        Text(
            text = "8269113752",
            fontSize = 18.sp,
            color = Color(android.graphics.Color.parseColor("#747679"))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 32.dp, bottom = 10.dp)
                .height(55.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(id = R.drawable.btn_1),
                    null,
                    modifier = Modifier
                        .width(30.dp).height(25.dp)
                        .padding(end = 5.dp)
                        .clickable { })
            }
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Suggestion",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    null,
                    Modifier
                        .height(25.dp).width(25.dp)
                        .padding(end = 5.dp)
                        .clickable { })
            }
        }



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                .height(55.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(id = R.drawable.btn_4),
                    null,
                    modifier = Modifier.width(30.dp).height(30.dp)
                        .padding(end = 5.dp)
                        .clickable { })
            }
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Share",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    null,
                    Modifier
                        .height(25.dp).width(25.dp)
                        .padding(end = 5.dp)
                        .clickable { })
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                .height(55.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(id = R.drawable.home),
                    null,
                    modifier = Modifier.height(22.dp)
                        .padding(end = 5.dp)
                        .clickable { })
            }
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Logout",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        navHostController.navigate(Routes.Intro)

                    }
                )
            }

        }
    }

}