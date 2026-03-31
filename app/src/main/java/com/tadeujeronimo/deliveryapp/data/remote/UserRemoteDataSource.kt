package com.tadeujeronimo.deliveryapp.data.remote

import com.tadeujeronimo.deliveryapp.data.models.User
import com.tadeujeronimo.deliveryapp.data.service.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRemoteDataSource constructor(private val userService: UserService) {

  suspend fun getUserById(idUser: String): Response<User> {
    val result: Response<User>

    withContext(Dispatchers.IO) {
      result = userService.getUserById(idUser)
    }

    return result
  }

  suspend fun getUsers(): Response<List<User>> {
    val result: Response<List<User>>

    withContext(Dispatchers.IO) {
      result = userService.getUsers()
    }

    return result
  }

    suspend fun createUser(user: User): Response<User> {
    val result: Response<User>

        withContext(Dispatchers.IO) {
      result = userService.createUser(user)
        }

        return result
    }

  suspend fun updateUser(idUser: String, user: User): Response<User> {
    val result: Response<User>

    withContext(Dispatchers.IO) {
      result = userService.updateUser(idUser, user)
    }

    return result
  }

  suspend fun deleteUser(idUser: String): Response<Map<String, String>> {
    val result: Response<Map<String, String>>

    withContext(Dispatchers.IO) {
      result = userService.deleteUser(idUser)
    }

    return result
  }
}