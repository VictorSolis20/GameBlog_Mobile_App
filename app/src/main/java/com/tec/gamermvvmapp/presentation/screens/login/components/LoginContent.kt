package com.tec.gamermvvmapp.presentation.screens.login.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.tec.gamermvvmapp.R
import com.tec.gamermvvmapp.domain.model.Response
import com.tec.gamermvvmapp.presentation.components.DefaultButton
import com.tec.gamermvvmapp.presentation.components.DefaultTextField
import com.tec.gamermvvmapp.presentation.screens.login.LoginViewModel
import com.tec.gamermvvmapp.presentation.ui.theme.Darkgray500
import com.tec.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme
import com.tec.gamermvvmapp.presentation.ui.theme.Red500

@Composable
fun LoginContent(viewModel: LoginViewModel = hiltViewModel()){

    val loginFlow = viewModel.loginFlow.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(Red500)
        ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.height(130.dp),
                    painter = painterResource(id = R.drawable.control),
                    contentDescription = "Control de xbox 360"
                )
                Text(
                    text = "FIREBASE MVVM"
                )
            }

        }
        Card(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, top = 200.dp)
                .background(Darkgray500)
        ){

            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 40.dp,
                            bottom = 0.dp,
                            start = 0.dp,
                            end = 0.dp
                        ),
                    text = "LOGIN",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Por favor inicia sesión para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = viewModel.email.value,
                    onValueChange = {viewModel.email.value = it},
                    label = "Correo electrónico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrMsg.value,
                    validateField = {
                        viewModel.validateEmail()
                    }
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = viewModel.password.value,
                    onValueChange = {viewModel.password.value = it},
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrMsg.value,
                    validateField = {
                        viewModel.validatePassword()
                    }
                )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    text = "INICIAR SESIÓN",
                    onClick = {
                        viewModel.login()
                    },
                    enabled = viewModel.isEnabledLoginButton
                )
            }
        }
    }

    loginFlow.value.let{
        when(it){
            // MOSTRAR QUE SE ESTA REALIZANDO LA PETICIÓN Y TODAVIA ESTA EN PROCESO
            Response.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    CircularProgressIndicator()
                }
            }
            is Response.Success -> {
                Toast.makeText(LocalContext.current, "Usuario logeado", Toast.LENGTH_LONG).show()
            }
            is Response.Failure -> {
                Toast.makeText(LocalContext.current, it.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
            }

            else -> {}
        }
    }
}