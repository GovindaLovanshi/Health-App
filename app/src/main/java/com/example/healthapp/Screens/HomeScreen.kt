package com.example.healthapp.Screens

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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

                Icon(painter = painterResource(id = R.drawable.btn_1),
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
                                        3 -> {navHostController.navigate(Routes.LabTestScreen)}
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
               NameAndProfile()
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
                           color = Color(android.graphics.Color.parseColor("#008000")),
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
                      List()
                      List()
                      List()
                      List()
                  }
              }
          }
        }


    }
}

@Composable
fun NameAndProfile() {
    ConstraintLayout(
        modifier = Modifier
            .padding(top = 48.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        val (name, order, img) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.profile), contentDescription = null,
            modifier = Modifier
                .constrainAs(img) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .clickable { }
        )
        Text(
            text = "Hi Gaju",
            color = colorResource(id = R.color.teal_700),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .constrainAs(name) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = "Health , Guide",
            color = Color.Black,

            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .constrainAs(order) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )
    }
}

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
                painter = painterResource(id = R.drawable.fav_bold),
                contentDescription = null,
                Modifier
                    .padding(top = 8.dp, bottom = 4.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp)
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
                painter = painterResource(id = R.drawable.fav_bold),
                contentDescription = null,
                Modifier
                    .padding(top = 8.dp, bottom = 4.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp)
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
                painter = painterResource(id = R.drawable.fav_bold),
                contentDescription = null,
                Modifier
                    .padding(top = 8.dp, bottom = 4.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp)
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
                painter = painterResource(id = R.drawable.fav_bold),
                contentDescription = null,
                Modifier
                    .padding(top = 8.dp, bottom = 4.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp)
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

@Composable
fun Card(){


}

@Composable
fun DoctorsList(){
    Row(modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)) {

        Text(
            text = "Category", color = Color.Black,
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
                painter = painterResource(id = R.drawable.fav_bold),
                contentDescription = null,
                Modifier
                    .padding(top = 8.dp, bottom = 4.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp)
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
                painter = painterResource(id = R.drawable.fav_bold),
                contentDescription = null,
                Modifier
                    .padding(top = 8.dp, bottom = 4.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp)
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
                painter = painterResource(id = R.drawable.fav_bold),
                contentDescription = null,
                Modifier
                    .padding(top = 8.dp, bottom = 4.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp)
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
                painter = painterResource(id =R.drawable.fav_bold),
                contentDescription = null,
                Modifier
                    .padding(top = 8.dp, bottom = 4.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp)
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




@Composable
fun TopView(){
    ConstraintLayout {
        val (topImg, profile) = createRefs()
        Box(Modifier
            .fillMaxWidth()
            .height(160.dp)
            .constrainAs(topImg) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }

            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(android.graphics.Color.parseColor("#008000")),
                        Color(android.graphics.Color.parseColor("#008000"))
                    )
                ), shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp)
            )
        )
        Row(
            modifier = Modifier
                .padding(top = 48.dp, start = 24.dp, end = 24.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .padding(start = 14.dp)
                    .weight(0.7f), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Hello", color = Color.White, fontSize = 18.sp
                )
                Text(
                    text = "Mr Gaju",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 14.dp)
                )

            }
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .clip(CircleShape)
                    .clickable { }
                , contentScale = ContentScale.Crop
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
                    .height(180.dp)
                    .constrainAs(topImg) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }, contentScale = ContentScale.Crop
            )

            Text(
                text = "title",
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
            Image(painter = painterResource(id = R.drawable.fav_bold),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(ownerIcon) {
                        start.linkTo(parent.start)
                        top.linkTo(topImg.bottom)
                    }
                    .padding(start = 16.dp, top = 16.dp)
            )

            Text(text = "{item.name}", modifier = Modifier
                .constrainAs(owner) {
                    start.linkTo(ownerIcon.end)
                    top.linkTo(ownerIcon.top)
                    bottom.linkTo(ownerIcon.bottom)
                }
                .padding(start = 16.dp, top = 16.dp)
            )
            Text(text = "400",
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

            Image(painter = painterResource(id = R.drawable.fav_bold),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(scoreIcon) {
                        end.linkTo(score.start)
                        top.linkTo(score.top)
                        bottom.linkTo(score.bottom)
                    }
                    .padding(end = 8.dp)
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
                text = "Find Your ",
                fontStyle = FontStyle.Italic
            )
        },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.arc_3),
                contentDescription = null,
                modifier = Modifier.size(23.dp)
            )
        }, shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = colorResource(id = R.color.grey),
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