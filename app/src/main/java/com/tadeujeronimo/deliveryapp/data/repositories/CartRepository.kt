package com.tadeujeronimo.deliveryapp.data.repositories

import com.tadeujeronimo.deliveryapp.data.models.CartProduct
import com.tadeujeronimo.deliveryapp.data.models.CartResponse
import com.tadeujeronimo.deliveryapp.data.remote.CartRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartRemoteDataSource: CartRemoteDataSource) {

    suspend fun createCart(cartCreate: CartProduct): Response<CartResponse> {
        return cartRemoteDataSource.createCart(cartCreate)
    }

    suspend fun getCart(idCart: String): Response<CartResponse> {
        return cartRemoteDataSource.getCart(idCart)
    }

    suspend fun updateCart(idCart: String, cartProduct: CartProduct): Response<CartResponse> {
        return cartRemoteDataSource.updateCart(idCart, cartProduct)
    }
}