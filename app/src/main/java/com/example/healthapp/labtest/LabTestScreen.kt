package com.example.healthapp.labtest

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.healthapp.Naviagtion.Routes
import com.example.healthapp.R

@Preview
@Composable
fun LabTestScreen(){

    Scaffold (
        topBar = {

        }

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
                TopBar()
            }

            item {
                Row(modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)) {

                    Text(
                        text = "Recommended Packages", color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "View all",
                        fontWeight = FontWeight.SemiBold,
                        color = Color(android.graphics.Color.parseColor("#0f4d0f")),
                        fontSize = 16.sp
                    )
                }

                HorizontalDivider()
            }



            item {
                Item()
                Item()
                Item()
            }
        }
    }

}

@Preview
@Composable
fun Item() {

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


            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Comprehensive Plus Full Body Checkup with Vitamin D B12 and Electrotypes",
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 2
                )

                Text(
                    text = "Includes 92 tests",
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
                        text = "Indore",
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
                        text = "Report in 21 Hrs",
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

@Composable
fun TopBar() {
    val horizontalPadding = 16.dp
    val verticalPadding = 16.dp

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (bluebox, title1, title2, profile, building, whiteBox) = createRefs()

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(255.dp)
            .background(color = colorResource(R.color.darkGreen))
            .constrainAs(bluebox) {
                top.linkTo(parent.top)
            }
        )

//        Image(painter = painterResource(R.drawable.profile),
//            contentDescription = null,
//            modifier = Modifier.constrainAs(building) {
//                bottom.linkTo(bluebox.bottom)
//            }
//        )
        Image(painter = painterResource(R.drawable.notification),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = horizontalPadding, vertical = verticalPadding)
                .constrainAs(profile) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = "सुप्रभात! ",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .constrainAs(title1) {
                    top.linkTo(profile.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = "आज आप क्या कर रहे हैं? \uD83D\uDE0A",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .constrainAs(title2) {
                    top.linkTo(title1.bottom)
                    bottom.linkTo(whiteBox.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        ConstraintLayout(modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .height(110.dp)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            .constrainAs(whiteBox) {
                top.linkTo(bluebox.bottom)
                bottom.linkTo(bluebox.bottom)
            }
            .clip(RoundedCornerShape(10.dp))
        ) {
            val (icon1, icon2, balance, reward, amount, wallet, arrow1, arrow2, arrow3, line1, line2) = createRefs()

            Image(painter = painterResource(R.drawable.mar),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = horizontalPadding, top = verticalPadding)
                    .width(20.dp)
                    .height(20.dp)
                    .constrainAs(icon1) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )

            Text(
                text = "WhatsApp ",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .constrainAs(wallet) {
                        bottom.linkTo(icon1.bottom)
                        start.linkTo(icon1.end)
                    }
                    .clickable {

                    }
            )
            Image(painter = painterResource(R.drawable.arrow),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = horizontalPadding)
                    .constrainAs(arrow1) {
                        top.linkTo(wallet.top)
                        bottom.linkTo(wallet.bottom)
                        start.linkTo(wallet.end)
                    }
            )

            Image(painter = painterResource(R.drawable.harvester),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = horizontalPadding, bottom = verticalPadding)
                    .width(20.dp)
                    .height(20.dp)
                    .constrainAs(icon2) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
            )
            Text(
                text = "Call",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .constrainAs(reward) {
                        top.linkTo(icon2.top)
                        start.linkTo(icon2.end)
                    }
            )

            Image(painter = painterResource(R.drawable.arrow),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = horizontalPadding)
                    .constrainAs(arrow2) {
                        top.linkTo(reward.top)
                        bottom.linkTo(reward.bottom)
                        start.linkTo(reward.end)
                    }
            )

            Box(modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .padding(vertical = verticalPadding)
                .background(colorResource(R.color.grey))
                .constrainAs(line1) {
                    centerTo(parent)
                }
            )
            Box(Modifier
                .height(1.dp)
                .width(170.dp)
                .padding(horizontal = 16.dp)
                .background(colorResource(R.color.grey))
                .constrainAs(line2) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }

            )
            Text(
                text = "List of Blood",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                color = Color.Black,
                modifier = Modifier
                    .padding(start = horizontalPadding, top = 32.dp)
                    .constrainAs(balance) {
                        top.linkTo(parent.top)
                        start.linkTo(line1.end)
                    }
            )
            Text(
                text = "Switch",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,

                color = Color.Black,
                modifier = Modifier
                    .padding(start = horizontalPadding, top = 8.dp)
                    .constrainAs(amount) {
                        top.linkTo(balance.bottom)
                        start.linkTo(balance.start)
                    }
                    .clickable {

                    }
            )
            Image(painter = painterResource(R.drawable.arrow),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = horizontalPadding)
                    .constrainAs(arrow3) {

                        bottom.linkTo(amount.bottom)
                        start.linkTo(amount.end)
                    }
            )
        }
    }
}