package com.tec.gamermvvmapp.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    var email: MutableState<String> = mutableStateOf("")
    var password: MutableState<String> = mutableStateOf("")
}