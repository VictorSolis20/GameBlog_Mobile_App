package com.tec.gamermvvmapp.domain.use_cases.auth

import com.tec.gamermvvmapp.domain.model.User
import com.tec.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class Signup @Inject constructor(private val repository: AuthRepository){

    suspend operator fun invoke(user: User) = repository.signUp(user)
}