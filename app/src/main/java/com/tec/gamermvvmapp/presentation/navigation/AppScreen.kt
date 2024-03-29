package com.tec.gamermvvmapp.presentation.navigation

sealed class AppScreen(val route: String) {

    object Login: AppScreen( "login")
    object Signup: AppScreen("signup")
}