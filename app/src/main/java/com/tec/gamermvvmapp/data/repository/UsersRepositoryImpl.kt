package com.tec.gamermvvmapp.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import com.tec.gamermvvmapp.domain.model.Response
import com.tec.gamermvvmapp.domain.model.User
import com.tec.gamermvvmapp.domain.repository.UsersRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val usersRef: CollectionReference): UsersRepository {


    override suspend fun create(user: User): Response<Boolean> {
        return try {
            user.password = ""
            usersRef.document(user.id).set(user).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }
}