package com.example.healthapp.Naviagtion

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.healthapp.AIChatBot.ChatPage
import com.example.healthapp.AIChatBot.ChatViewModel
import com.example.healthapp.Authentication.view.RegistrationScreen
import com.example.healthapp.Doctor.form.DoctorForm
import com.example.healthapp.Doctor.view.DoctorList
import com.example.healthapp.Naviagtion.Routes.ChatActivity
import com.example.healthapp.Naviagtion.Routes.Intro
import com.example.healthapp.Screens.HomeScreen
import com.example.healthapp.Screens.Intro
import com.example.healthapp.Screens.Profile
import com.example.healthapp.Screens.Setting
import com.example.healthapp.Screens.SplashScreen
import com.example.healthapp.blood.form.BloodForm
import com.example.healthapp.blood.view.DonaterList


@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
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

            Profile(navController)
        }

        composable<Routes.DonaterList> {
            DonaterList(navController)

        }
        composable<Routes.DoctorList> {
            DoctorList(navController)

        }

        composable<Routes.RegistrationScreen>{
            RegistrationScreen(navController)
        }

        composable<Routes.Setting>{
            Setting(navController)
        }

        composable<Routes.BloodForm>{
            BloodForm(navController)
        }

        composable<Routes.DoctorForm>{
            DoctorForm(navController)
        }

        composable<Routes.Intro>{
            Intro(navController)

        }

        composable <Routes.ChatActivity>{
            ChatPage()

        }

    }
}