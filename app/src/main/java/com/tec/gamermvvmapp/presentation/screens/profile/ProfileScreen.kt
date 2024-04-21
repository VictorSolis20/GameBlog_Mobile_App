package com.tec.gamermvvmapp.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navHostController: NavHostController){
    Scaffold(
        topBar = {},
        content = {
            Text(text = "ProfileScreen")
        },
        bottomBar = {}
    )
}