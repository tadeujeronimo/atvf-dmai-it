package com.tadeujeronimo.deliveryapp.data.remote

import com.tadeujeronimo.deliveryapp.data.models.UserLogin
import com.tadeujeronimo.deliveryapp.data.models.UserLoginResponse
import com.tadeujeronimo.deliveryapp.data.service.LoginService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRemoteDataSource constructor(private val loginService: LoginService) {

    suspend fun makeLogin(userLogin: UserLogin): Response<UserLoginResponse> {
        var result: Response<UserLoginResponse>

        withContext(Dispatchers.IO) {
          result = loginService.makeLogin(userLogin)
        }

        return result
    }
}