package com.tec.gamermvvmapp.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.tec.gamermvvmapp.domain.model.Response

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    fun logout()
}