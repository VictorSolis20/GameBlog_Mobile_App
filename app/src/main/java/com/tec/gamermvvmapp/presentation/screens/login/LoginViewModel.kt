package com.tec.gamermvvmapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.tec.gamermvvmapp.domain.model.Response
import com.tec.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {

    // STATE FORM
    var state by mutableStateOf(LoginState())
    private set

    // EMAIL
    var isEmailValid: Boolean by mutableStateOf(false)
    var emailErrMsg: String by mutableStateOf("")

    // PASSWORD
    var isPasswordValid: Boolean by mutableStateOf(false)
    var passwordErrMsg: String by mutableStateOf("")

    // ENABLE BUTTON
    var isEnabledLoginButton = false

    // LOGIN RESPONSE
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)

    val currentUser = authUseCases.getCurrentUser()

    init {
        if(currentUser != null){ // SESIÓN INICIADA
            loginResponse = Response.Success(currentUser)
        }
    }

    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }

    fun login() = viewModelScope.launch{
        loginResponse = Response.Loading
        val result = authUseCases.login(state.email, state.password)
        loginResponse = result
    }

    fun enabledLoginButton(){
        isEnabledLoginButton = isEmailValid && isPasswordValid
    }

    fun validateEmail(){
        // ES UN EMAIL VALIDO
        if(Patterns.EMAIL_ADDRESS.matcher(state.email).matches()){
            isEmailValid = true
            emailErrMsg = ""
        }else{
            isEmailValid = false
            emailErrMsg = "El email no es valido"
        }

        enabledLoginButton()
    }

    fun validatePassword(){
        if(state.password.length >= 6){
            isPasswordValid = true
            passwordErrMsg = ""
        }else{
            isPasswordValid = false
            passwordErrMsg = "Al menos 6 caracteres"
        }

        enabledLoginButton()
    }
}