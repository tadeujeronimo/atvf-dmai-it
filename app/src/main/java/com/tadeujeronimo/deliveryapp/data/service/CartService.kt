package com.tadeujeronimo.deliveryapp.data.service

import com.tadeujeronimo.deliveryapp.data.models.CartProduct
import com.tadeujeronimo.deliveryapp.data.models.CartResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CartService {
    @POST("carrinho/create")
    suspend fun createCart(@Body cartCreate: CartProduct): Response<CartResponse>

    @GET("carrinho/find/{idCart}")
    suspend fun getCart(@Path("idCart") idCart: String): Response<CartResponse>

    @PUT("carrinho/update/{idCart}")
    suspend fun updateCart(@Path("idCart") idCart: String, @Body cartProduct: CartProduct): Response<CartResponse>
}