package com.tec.gamermvvmapp.domain.repository

import com.tec.gamermvvmapp.domain.model.Response
import com.tec.gamermvvmapp.domain.model.User
import kotlinx.coroutines.flow.Flow


interface UsersRepository {

    suspend fun create(user: User): Response<Boolean>
    fun getUserById(id: String): Flow<User>

}