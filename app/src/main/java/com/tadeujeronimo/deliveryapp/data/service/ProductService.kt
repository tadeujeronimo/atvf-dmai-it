package com.tadeujeronimo.deliveryapp.data.service

import com.tadeujeronimo.deliveryapp.data.models.Product
import retrofit2.Response
import retrofit2.http.*

interface ProductService {
    @GET("produto/findAll")
    suspend fun getProducts(): Response<List<Product>>

    @POST("produto/create")
    suspend fun createProduct(@Body product: Product): Response<Product>

    @PUT("produto/update/{idProduct}")
    suspend fun updateProduct(@Path("idProduct") idProduct: String?, @Body product: Product): Response<Product>

    @DELETE("produto/delete/{idProduct}")
    suspend fun deleteProduct(@Path("idProduct") idProduct: String?): Response<Product>
}