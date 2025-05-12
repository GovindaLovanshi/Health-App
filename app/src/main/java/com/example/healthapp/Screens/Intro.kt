package com.example.healthapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.healthapp.Naviagtion.Routes
import com.example.healthapp.R


@Composable


fun Intro(navHostController: NavHostController) {



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkGreen))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.icon),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 48.dp)
                .fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(76.dp))
//        Text(
//            text = "Smart  Solution \n" +
//                    " WIth Service Features\n" +
//                    "and Job Search",
//            color = colorResource(R.color.black),
//            textAlign = TextAlign.Center, fontSize = 26.sp,
//            fontWeight = FontWeight.SemiBold,
//            lineHeight = 40.sp,
//            modifier = Modifier.padding(top = 16.dp)
//        )

        Spacer(modifier = Modifier.height(76.dp))

        Button(onClick={
            navHostController.navigate(Routes.HomeScreen)
        },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.green)
            ),
            modifier = Modifier
                .padding(top=16.dp)
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Let's Get Started",
                fontSize = 18.sp,
                color= Color.White
            )
        }
//        Text(
//            text = "Already have an account? Sign In"
//            , color = colorResource(R.color.darkBrown),
//            textAlign = TextAlign.Center,
//            fontSize = 18.sp,
//            lineHeight = 30.sp,
//            modifier = Modifier.padding(top=16.dp)
//        )
    }
}