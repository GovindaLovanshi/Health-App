package com.example.healthapp.Naviagtion

import kotlinx.serialization.Serializable

sealed class Routes {

     @Serializable
     data object SplashScreen:Routes()


    @Serializable
    data object RegistrationScreen:Routes()


    @Serializable
    data object HomeScreen:Routes()

    @Serializable
    data object Profile:Routes()

    @Serializable
    data object DonaterList:Routes()

    @Serializable
    data object DoctorList:Routes()


    @Serializable
    data object Setting:Routes()


    @Serializable
    data object Intro:Routes()
}