package com.tec.gamermvvmapp.domain.use_cases.auth

import com.tec.gamermvvmapp.data.repository.AuthRepositoryImpl
import com.tec.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(private val repository: AuthRepository){
    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}