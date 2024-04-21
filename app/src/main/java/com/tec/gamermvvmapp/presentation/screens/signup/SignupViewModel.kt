package com.tec.gamermvvmapp.presentation.screens.signup

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.tec.gamermvvmapp.domain.model.Response
import com.tec.gamermvvmapp.domain.model.User
import com.tec.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {

    // USERNAME
    var username: MutableState<String> = mutableStateOf("")
    var isUsernameValid: MutableState<Boolean> = mutableStateOf(false)
    var usernameErrMsg: MutableState<String> = mutableStateOf("")

    // EMAIL
    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")

    // PASSWORD
    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    // ENABLE BUTTON
    var isEnabledLoginButton = false

    // CONFIRMAR CONTRASENA
    var confirmPassword: MutableState<String> = mutableStateOf("")
    var isconfirmPassword: MutableState<Boolean> = mutableStateOf(false)
    var confirmPasswordErrMsg: MutableState<String> = mutableStateOf("")

    private val _signupFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Response<FirebaseUser>?> = _signupFlow

    fun onSignup(){
        val user = User(
            username = username.value,
            email = email.value,
            password = password.value
        )

        signup(user)
    }

    fun signup(user: User) = viewModelScope.launch {
        _signupFlow.value = Response.Loading
        val result = authUseCases.signup(user)
        _signupFlow.value = result
    }

    fun enabledLoginButton(){
        isEnabledLoginButton =
            isEmailValid.value &&
            isPasswordValid.value &&
            isUsernameValid.value &&
            isconfirmPassword.value
    }

    fun validateConfirmPassword(){
        if(password.value == confirmPassword.value){
            isconfirmPassword.value = true
            confirmPasswordErrMsg.value = ""
        }
        else{
            isconfirmPassword.value = false
            confirmPasswordErrMsg.value = "Las contraseñas no coinciden"
        }
        enabledLoginButton()
    }

    fun validateUsername(){
        if(username.value.length >= 5){
            isUsernameValid.value = true
            usernameErrMsg.value = ""
        }
        else{
            isUsernameValid.value = false
            usernameErrMsg.value = "Al menos 5 caracteres"
        }
        enabledLoginButton()
    }

    fun validateEmail(){
        // ES UN EMAIL VALIDO
        if(Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            isEmailValid.value = true
            emailErrMsg.value = ""
        }else{
            isEmailValid.value = false
            emailErrMsg.value = "El email no es valido"
        }

        enabledLoginButton()
    }

    fun validatePassword(){
        if(password.value.length >= 6){
            isPasswordValid.value = true
            passwordErrMsg.value = ""
        }else{
            isPasswordValid.value = false
            passwordErrMsg.value = "Al menos 6 caracteres"
        }

        enabledLoginButton()
    }
}