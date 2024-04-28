package com.tec.gamermvvmapp.domain.repository

import com.tec.gamermvvmapp.domain.model.Response
import com.tec.gamermvvmapp.domain.model.User

interface UsersRepository {

    suspend fun create(user: User): Response<Boolean>
}