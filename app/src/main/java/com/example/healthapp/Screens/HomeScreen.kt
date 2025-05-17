package com.example.healthapp.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorDirection
import com.example.bottombar.model.IndicatorStyle
import com.example.healthapp.Naviagtion.Routes
import com.example.healthapp.R
import com.example.healthapp.model.Items

@SuppressLint("ResourceAsColor")
@Composable
fun HomeScreen(navHostController: NavHostController) {
    val context = LocalContext.current

    var selectedItem by remember { mutableIntStateOf(0) }
    val shouldShowBottomBar = remember { mutableStateOf(true) }



    val BottomNavItem = listOf(
        BottomNavItem("Home", Icons.Default.Home, unseletedIcon = Icons.Outlined.Home),
        BottomNavItem("Blood", Icons.Default.AddCircle, unseletedIcon = Icons.Outlined.AddCircle),
        BottomNavItem("Doctor", Icons.Default.Person, unseletedIcon = Icons.Outlined.Person),
        BottomNavItem("Lab", Icons.Default.MoreVert, unseletedIcon = Icons.Outlined.MoreVert),
        BottomNavItem("Setting", Icons.Default.Settings, unseletedIcon = Icons.Outlined.Settings),
    )


    Scaffold(
        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Routes.ChatActivity)

                },
                modifier = Modifier.size(65.dp),
                contentColor = Color.White,
            ) {

                Icon(painter = painterResource(id = R.drawable.add),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp))
            }
        },
        topBar = {


        },

        bottomBar = {


            if (shouldShowBottomBar.value) {

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = WindowInsets.navigationBars            // this automaticaly adjust the hight of app bottom bar according to system navigation home, back etc
                                .asPaddingValues()
                                .calculateBottomPadding()
                        )
                    // Fixed height for bottom bar
//                    color = Color.Cyan,
                ) {
                    AnimatedBottomBar(


                        selectedItem = selectedItem,
                        itemSize = BottomNavItem.size,


//                        modifier = Modifier.padding(bottom =  WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()),

                        containerColor = Color.Transparent,
                        // indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                        indicatorColor = colorResource(id = R.color.teal_700),
                        contentColor = MaterialTheme.colorScheme.primary,

                        indicatorDirection = IndicatorDirection.BOTTOM,

                        indicatorStyle = IndicatorStyle.FILLED,


                        ) {
                        BottomNavItem.forEachIndexed { index, navigationItem ->
                            BottomBarItem(
                                modifier = Modifier.align(alignment = Alignment.Top),
                                selected = selectedItem == index,
                                onClick = {

                                    selectedItem = index
                                    when(index){

                                        0 -> {navHostController.navigate(Routes.HomeScreen)}
                                        1 -> {navHostController.navigate(Routes.DonaterList)}
                                        2 -> {navHostController.navigate(Routes.DoctorList)}
                                        3 -> {navHostController.navigate(Routes.AppointmentForm)}
                                        4 -> {navHostController.navigate(Routes.Setting)}
                                    }


                                },
                                imageVector = navigationItem.icon,
                                label = navigationItem.name,
                                containerColor = Color.Transparent,
                            )
                        }
                    }

                }


            }
//            MyBottomBar(navHostController , selectedItem = 0, onClick = { index ->
//
//                when(index){
//
//                    0 -> {navHostController.navigate(Routes.HomeScreen)}
//                    1 -> {navHostController.navigate(Routes.DoctorList)}
//                    2 -> {navHostController.navigate(Routes.DonaterList)}
//                    3 -> {navHostController.navigate(Routes.Setting)}
//                }
//
//            })
        }
    ) { paddingValues ->
        LazyColumn(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(paddingValues)
                .background(color = colorResource(R.color.lightBlue)),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

           item{

               TopBar()

           }

//            item {
//                Search()
//            }


           item{
               ConstraintLayout(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                       .height(160.dp)
                       .background(
                           color = colorResource(R.color.darkGreen),
                           shape = RoundedCornerShape(10.dp)
                       )
               ) {
                   val (img, text, button) = createRefs()
                   Image(painter = painterResource(id = R.drawable.girl2),
                       contentDescription = null,
                       modifier = Modifier
                           .constrainAs(img) {
                               top.linkTo(parent.top)
                               bottom.linkTo(parent.bottom)
                               end.linkTo(parent.end)
                           }
                   )
                   Text(text = "Advanced Human Test \nFacility in This Plateform",
                       fontSize = 18.sp,
                       fontWeight = FontWeight.Bold,
                       color = Color.White,
                       modifier = Modifier
                           .padding(start = 16.dp, top = 16.dp)
                           .constrainAs(text) {
                               top.linkTo(parent.top)
                               start.linkTo(parent.start)
                           }
                   )
                   Text(text = "Book Now",
                       fontSize = 14.sp,
                       fontWeight = FontWeight.SemiBold,
                       color = Color(android.graphics.Color.parseColor("#521c98")),
                       modifier = Modifier
                           .padding(start = 16.dp, top = 16.dp)
                           .constrainAs(button) {
                               top.linkTo(text.bottom)
                               bottom.linkTo(parent.bottom)
                           }
                           .clickable {
                               navHostController.navigate(Routes.LabTestScreen)
                           }

                           .background(
                               Color(android.graphics.Color.parseColor("#f0e9fa")),
                               shape = RoundedCornerShape(10.dp)
                           )
                           .padding(8.dp)
                   )

               }

           }

            item {
                CategoryDR()
            }

            item {
                Row(modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)) {

                    Text(
                        text = "Nearest Doctors", color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "See all",
                        fontWeight = FontWeight.SemiBold,
                        color = Color(android.graphics.Color.parseColor("#0f4d0f")),
                        fontSize = 16.sp
                    )
                }
            }

          item{
              LazyRow(
                  modifier = Modifier
                      .fillMaxWidth(),
                  horizontalArrangement = Arrangement.spacedBy(12.dp),
                  contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp)
              ) {
                  item {
                      List()
                      ListItem()

                  }
              }
          }
        }


    }
}

@Preview
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
            .height(200.dp)
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
        Image(painter = painterResource(R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = horizontalPadding, vertical = verticalPadding)
                .constrainAs(profile) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = "Good Morning!! ",
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
            text = "",
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

            Image(painter = painterResource(R.drawable.whatsapp),
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

            Image(painter = painterResource(R.drawable.phone),
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


@Preview
@Composable
fun CategoryDR(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)

    ) {
        Column(
            modifier = Modifier.weight(0.25f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.brain),
                contentDescription = null,
                Modifier
                    .padding(top = 8.dp, bottom = 4.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp)
                    .width(20.dp)
                    .height(20.dp)
            )

            Text(
                text = "Brain",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 8.dp),
                color = Color(android.graphics.Color.parseColor("#0f4d0f")),
            )
        }

        Column(
            modifier = Modifier.weight(0.25f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.neurosurgeon),
                contentDescription = null,
                Modifier
                    .padding(top = 8.dp, bottom = 4.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp)
                    .width(20.dp)
                    .height(20.dp)
            )

            Text(
                text = "Neuro",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 8.dp),
                color = Color(android.graphics.Color.parseColor("#0f4d0f")),
            )
        }

        Column(
            modifier = Modifier.weight(0.25f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = null,
                Modifier
                    .padding(top = 8.dp, bottom = 4.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp)
                    .width(20.dp)
                    .height(20.dp)
            )

            Text(
                text = "Heart",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 8.dp),
                color = Color(android.graphics.Color.parseColor("#0f4d0f")),
            )
        }

        Column(
            modifier = Modifier.weight(0.25f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.tooth),
                contentDescription = null,
                Modifier
                    .padding(top = 8.dp, bottom = 4.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp)
                    .width(20.dp)
                    .height(20.dp)
            )

            Text(
                text = "Dentist",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 8.dp),
                color = Color(android.graphics.Color.parseColor("#0f4d0f")),
            )
        }


    }
}


@Preview
@Composable
fun List(){
    Column(
        modifier = Modifier
            .height(270.dp)
            .width(250.dp)
            .shadow(3.dp, shape = RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .clickable {

            }
    ) {
        ConstraintLayout(modifier = Modifier.height(IntrinsicSize.Max)) {
            val (topImg, title, owner, ownerIcon, price, score, scoreIcon) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.doctor), contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .constrainAs(topImg) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }, contentScale = ContentScale.Crop
            )

            Text(
                text = "Brain Specialist",
                Modifier
                    .background(Color(android.graphics.Color.parseColor("#90000000")))
                    .fillMaxWidth()
                    .padding(6.dp)
                    .constrainAs(title) {
                        bottom.linkTo(topImg.bottom)
                        start.linkTo(parent.start)
                    },
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 14.sp
            )
            Image(painter = painterResource(id = R.drawable.doctor),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(ownerIcon) {
                        start.linkTo(parent.start)
                        top.linkTo(topImg.bottom)
                    }
                    .padding(start = 16.dp, top = 16.dp)
                    .width(20.dp)
                    .height(20.dp)
            )

            Text(text = "Dr RK Brown", modifier = Modifier
                .constrainAs(owner) {
                    start.linkTo(ownerIcon.end)
                    top.linkTo(ownerIcon.top)
                    bottom.linkTo(ownerIcon.bottom)
                }
                .padding(start = 16.dp, top = 16.dp)
                , fontWeight = FontWeight.SemiBold
                , fontSize = 12.sp
            )
            Text(text = "₹1000",
                fontWeight = FontWeight.Bold,
                color = Color(android.graphics.Color.parseColor("#521c98")),
                modifier = Modifier
                    .constrainAs(price) {
                        start.linkTo(ownerIcon.start)
                        top.linkTo(ownerIcon.bottom)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(start = 16.dp, top = 8.dp)
            )
            Text(
                text = "4.6",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(score)
                    {
                        end.linkTo(parent.end)
                        top.linkTo(price.top)
                        bottom.linkTo(price.bottom)
                    }
                    .padding(end = 16.dp)
            )

            Image(painter = painterResource(id = R.drawable.star),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(scoreIcon) {
                        end.linkTo(score.start)
                        top.linkTo(score.top)
                        bottom.linkTo(score.bottom)
                    }
                    .padding(end = 8.dp)
                    .width(20.dp)
                    .height(20.dp)

            )
        }
    }
    Spacer(modifier = Modifier.width(10.dp))
}
@Preview
@Composable
fun ListItem(){
    Column(
        modifier = Modifier
            .height(270.dp)
            .width(250.dp)
            .shadow(3.dp, shape = RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .clickable {

            }
    ) {
        ConstraintLayout(modifier = Modifier.height(IntrinsicSize.Max)) {
            val (topImg, title, owner, ownerIcon, price, score, scoreIcon) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.doct2), contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .constrainAs(topImg) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }, contentScale = ContentScale.Crop
            )

            Text(
                text = "Heart Specialist",
                Modifier
                    .background(Color(android.graphics.Color.parseColor("#90000000")))
                    .fillMaxWidth()
                    .padding(6.dp)
                    .constrainAs(title) {
                        bottom.linkTo(topImg.bottom)
                        start.linkTo(parent.start)
                    },
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 14.sp
            )
            Image(painter = painterResource(id = R.drawable.doct2),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(ownerIcon) {
                        start.linkTo(parent.start)
                        top.linkTo(topImg.bottom)
                    }
                    .padding(start = 16.dp, top = 16.dp)
                    .width(20.dp)
                    .height(20.dp)
            )

            Text(text = "Dr Harry ", modifier = Modifier
                .constrainAs(owner) {
                    start.linkTo(ownerIcon.end)
                    top.linkTo(ownerIcon.top)
                    bottom.linkTo(ownerIcon.bottom)
                }
                .padding(start = 16.dp, top = 16.dp)
                , fontWeight = FontWeight.SemiBold
                , fontSize = 12.sp
            )
            Text(text = "₹1400",
                fontWeight = FontWeight.Bold,
                color = Color(android.graphics.Color.parseColor("#521c98")),
                modifier = Modifier
                    .constrainAs(price) {
                        start.linkTo(ownerIcon.start)
                        top.linkTo(ownerIcon.bottom)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(start = 16.dp, top = 8.dp)
            )
            Text(
                text = "4.8",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(score)
                    {
                        end.linkTo(parent.end)
                        top.linkTo(price.top)
                        bottom.linkTo(price.bottom)
                    }
                    .padding(end = 16.dp)
            )

            Image(painter = painterResource(id = R.drawable.star),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(scoreIcon) {
                        end.linkTo(score.start)
                        top.linkTo(score.top)
                        bottom.linkTo(score.bottom)
                    }
                    .padding(end = 8.dp)
                    .width(20.dp)
                    .height(20.dp)

            )
        }
    }
    Spacer(modifier = Modifier.width(10.dp))
}



@Composable
fun Search() {
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        value = text, onValueChange = { text = it },
        label = {
            Text(
                text = "Search Test.... ",
                fontStyle = FontStyle.Italic
            )
        },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = null,
                modifier = Modifier.size(23.dp)
            )
        }, shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = colorResource(id = R.color.white),
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            textColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
            unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .height(50.dp)
            .background(colorResource(id = R.color.grey), CircleShape)
    )
}

data class BottomNavItem(val name: String, val icon: ImageVector, val unseletedIcon: ImageVector)