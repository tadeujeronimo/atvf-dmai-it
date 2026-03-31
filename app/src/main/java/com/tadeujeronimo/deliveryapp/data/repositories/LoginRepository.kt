package com.tadeujeronimo.deliveryapp.data.repositories

import com.tadeujeronimo.deliveryapp.data.models.UserLogin
import com.tadeujeronimo.deliveryapp.data.models.UserLoginResponse
import com.tadeujeronimo.deliveryapp.data.remote.LoginRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginRemoteDataSource: LoginRemoteDataSource) {

    suspend fun makeLogin(userLogin: UserLogin): Response<UserLoginResponse> {
        return loginRemoteDataSource.makeLogin(userLogin)
    }
}