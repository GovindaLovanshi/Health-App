package com.example.healthapp.Naviagtion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.healthapp.Authentication.view.RegistrationScreen
import com.example.healthapp.Doctor.view.DoctorList
import com.example.healthapp.Screens.HomeScreen
import com.example.healthapp.Screens.Intro
import com.example.healthapp.Screens.Profile
import com.example.healthapp.Screens.Setting
import com.example.healthapp.Screens.SplashScreen
import com.example.healthapp.blood.view.DonaterList


@Composable
fun Navigation(){

    val navController =  rememberNavController()

    NavHost(startDestination = Routes.SplashScreen, navController = navController){


        composable<Routes.SplashScreen> {

            SplashScreen(navController)
        }

        composable<Routes.HomeScreen> {

            HomeScreen(navController)
        }

        composable<Routes.Profile> {

            Profile()
        }

        composable<Routes.DonaterList> {
            DonaterList()

        }
        composable<Routes.DoctorList> {
            DoctorList()

        }

        composable<Routes.RegistrationScreen>{
            RegistrationScreen(navController)
        }

        composable<Routes.Setting>{
            Setting()
        }

        composable<Routes.Intro>{
            Intro(navController)
        }


    }
}