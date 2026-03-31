package com.tadeujeronimo.deliveryapp.data.service

import com.tadeujeronimo.deliveryapp.data.models.UserLogin
import com.tadeujeronimo.deliveryapp.data.models.UserLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("auth/login")
    suspend fun makeLogin(@Body userLogin: UserLogin): Response<UserLoginResponse>

}