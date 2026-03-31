package com.tadeujeronimo.deliveryapp.data.remote

import com.tadeujeronimo.deliveryapp.data.models.CartProduct
import com.tadeujeronimo.deliveryapp.data.models.CartResponse
import com.tadeujeronimo.deliveryapp.data.service.CartService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CartRemoteDataSource constructor(private val cartService: CartService) {

    suspend fun createCart(cartCreate: CartProduct): Response<CartResponse> {
        var result: Response<CartResponse>

        withContext(Dispatchers.IO) {
            result = cartService.createCart(cartCreate)
        }

        return result
    }


    suspend fun getCart(idCart: String): Response<CartResponse> {
        var result: Response<CartResponse>

        withContext(Dispatchers.IO) {
            result = cartService.getCart(idCart)
        }

        return result
    }

    suspend fun updateCart(idCart: String, cartProduct: CartProduct): Response<CartResponse> {
        var result: Response<CartResponse>

        withContext(Dispatchers.IO) {
            result = cartService.updateCart(idCart, cartProduct)
        }

        return result
    }
}