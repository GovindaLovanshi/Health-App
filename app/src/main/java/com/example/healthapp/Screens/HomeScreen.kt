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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        BottomNavItem("Setting", Icons.Default.Person, unseletedIcon = Icons.Outlined.Settings),
    )


    Scaffold(
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
                                        3 -> {navHostController.navigate(Routes.Setting)}
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
               TopView()
           }


           item{
               Card()
           }

            item {
                CategoryDR()
            }



        }
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
        Text(text = "Advanced Medical \nFacility in This Plateform",
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

                .background(
                    Color(android.graphics.Color.parseColor("#f0e9fa")),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(8.dp)
        )

    }

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
                painter = painterResource(id = R.drawable.bell_icon),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clickable { }
            )

        }


    }
}

data class BottomNavItem(val name: String, val icon: ImageVector, val unseletedIcon: ImageVector)