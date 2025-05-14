package com.example.healthapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.healthapp.Naviagtion.Routes
import com.example.healthapp.R




@Composable
fun Intro(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ConstraintLayout (
            modifier = Modifier
                .background(Color.White)
        ){
            val (backgroundImge, logoImg,titleTxt,subtitleTxt,buttonBox)=createRefs()
            Image(
                painter = painterResource(id = R.drawable.background_intro),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(backgroundImge){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .height(700.dp),
                contentScale = ContentScale.FillBounds
            )
            androidx.compose.material3.Text(
                text = "Health Guide",
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xff3d33a8),
                modifier = Modifier
                    .padding(top = 102.dp)
                    .constrainAs(titleTxt) {
                        bottom.linkTo(logoImg.top, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = null,
                modifier = Modifier.run {
                    constrainAs(logoImg){
                        top.linkTo(backgroundImge.top)
                        bottom.linkTo(backgroundImge.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                        .width(300.dp)
                        .height(300.dp)
                }
                , contentScale = ContentScale.Fit
            )
            androidx.compose.material3.Text(
                text = "Health Guide App",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xff3d33a8),
                modifier = Modifier
                    .padding(top = 102.dp)
                    .constrainAs(subtitleTxt) {
                        top.linkTo(logoImg.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Button(onClick={
                navHostController.navigate(Routes.HomeScreen)
            },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.green)
                ),
                modifier = Modifier
                    .padding(top=16.dp)
                    .width(100.dp)
                    .height(50.dp)
                    .constrainAs(buttonBox){
                        top.linkTo(backgroundImge.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(
                    text = "Let's Get Started",
                    fontSize = 18.sp,
                    color= Color.White
                )
            }
        }
    }

}


//        Text(
//            text = "Already have an account? Sign In"
//            , color = colorResource(R.color.darkBrown),
//            textAlign = TextAlign.Center,
//            fontSize = 18.sp,
//            lineHeight = 30.sp,
//            modifier = Modifier.padding(top=16.dp)
//        )
