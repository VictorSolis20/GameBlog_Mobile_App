package com.tec.gamermvvmapp.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.tec.gamermvvmapp.core.Constants.USERS
import com.tec.gamermvvmapp.data.repository.AuthRepositoryImpl
import com.tec.gamermvvmapp.data.repository.UsersRepositoryImpl
import com.tec.gamermvvmapp.domain.repository.AuthRepository
import com.tec.gamermvvmapp.domain.repository.UsersRepository
import com.tec.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.tec.gamermvvmapp.domain.use_cases.auth.GetCurrentUser
import com.tec.gamermvvmapp.domain.use_cases.auth.Login
import com.tec.gamermvvmapp.domain.use_cases.auth.Logout
import com.tec.gamermvvmapp.domain.use_cases.auth.Signup
import com.tec.gamermvvmapp.domain.use_cases.users.Create
import com.tec.gamermvvmapp.domain.use_cases.users.UsersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signup = Signup(repository)
    )

    @Provides
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        create = Create(repository)
    )
}