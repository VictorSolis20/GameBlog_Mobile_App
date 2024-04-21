package com.tec.gamermvvmapp.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tec.gamermvvmapp.presentation.components.DefaultButton
import com.tec.gamermvvmapp.presentation.navigation.AppScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()){
    Scaffold(
        topBar = {},
        content = {
            DefaultButton(
                modifier = Modifier,
                text = "Cerrar sesión",
                onClick = {
                    viewModel.logout()
                    navController.navigate(route = AppScreen.Login.route){
                        popUpTo(AppScreen.Profile.route){inclusive = true}
                    }
                })
        },
        bottomBar = {}
    )
}