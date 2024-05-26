package com.tec.gamermvvmapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tec.gamermvvmapp.presentation.screens.login.LoginScreen
import com.tec.gamermvvmapp.presentation.screens.profile.ProfileScreen
import com.tec.gamermvvmapp.presentation.screens.profile_edit.ProfileEditScreen
import com.tec.gamermvvmapp.presentation.screens.signup.SignupScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = AppScreen.Login.route
    ){
        composable(route = AppScreen.Login.route){
            LoginScreen(navController)
        }

        composable(route = AppScreen.Signup.route){
            SignupScreen(navController)
        }

        composable(route = AppScreen.Profile.route){
            ProfileScreen(navController)
        }

        composable(route = AppScreen.ProfileEdit.route){
            ProfileEditScreen(navController)
        }
    }
}