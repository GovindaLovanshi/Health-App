package com.example.healthapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorDirection
import com.example.bottombar.model.IndicatorStyle
import com.example.healthapp.Naviagtion.Routes
import com.example.healthapp.R

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
                                        1 -> {navHostController.navigate(Routes.DoctorList)}
                                        2 -> {navHostController.navigate(Routes.DonaterList)}
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



        }
    }
}

data class BottomNavItem(val name: String, val icon: ImageVector, val unseletedIcon: ImageVector)