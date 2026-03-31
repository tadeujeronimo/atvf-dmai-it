package com.tadeujeronimo.deliveryapp.data.service

import com.tadeujeronimo.deliveryapp.data.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("usuario/findById/{idUser}")
    suspend fun getUserById(@Path("idUser") idUser: String): Response<User>

    @GET("usuario/findAll")
    suspend fun getUsers(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<List<User>>

    @POST("usuario/create")
    suspend fun createUser(@Body user: User): Response<User>

    @PUT("usuario/update/{idUser}")
    suspend fun updateUser(@Path("idUser") idUser: String, @Body user: User): Response<User>

    @DELETE("usuario/remove/{idUser}")
    suspend fun deleteUser(@Path("idUser") idUser: String): Response<Map<String, String>>
}