package com.tadeujeronimo.deliveryapp.data.repositories

import com.tadeujeronimo.deliveryapp.data.models.User
import com.tadeujeronimo.deliveryapp.data.remote.UserRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource) {

    suspend fun getUserById(idUser: String): Response<User> {
        return userRemoteDataSource.getUserById(idUser)
    }

    suspend fun getUsers(): Response<List<User>> {
        return userRemoteDataSource.getUsers()
    }

    suspend fun createUser(user: User): Response<User> {
        return userRemoteDataSource.createUser(user)
    }

    suspend fun updateUser(idUser: String, user: User): Response<User> {
        return userRemoteDataSource.updateUser(idUser, user)
    }

    suspend fun deleteUser(idUser: String): Response<Map<String, String>> {
        return userRemoteDataSource.deleteUser(idUser)
    }
}