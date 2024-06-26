package com.tec.gamermvvmapp.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tec.gamermvvmapp.domain.model.Response
import com.tec.gamermvvmapp.presentation.navigation.AppScreen
import com.tec.gamermvvmapp.presentation.screens.login.LoginViewModel

@Composable
fun Login(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()){
    when(val loginResponse = viewModel.loginResponse){
        // MOSTRAR QUE SE ESTA REALIZANDO LA PETICIÓN Y TODAVIA ESTA EN PROCESO
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                navController.navigate(route = AppScreen.Profile.route){
                    popUpTo(AppScreen.Login.route){inclusive = true}
                }
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, loginResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }

        else -> {}
    }
}