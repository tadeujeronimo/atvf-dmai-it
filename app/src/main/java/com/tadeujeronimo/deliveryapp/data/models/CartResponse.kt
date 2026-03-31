package com.tadeujeronimo.deliveryapp.data.models

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("_id")
    val cartId: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("produtos")
    val products: List<Product> = listOf(),
    @SerializedName("precoTotal")
    val totalPrice: Double,
    @SerializedName("frete")
    val freight: Double
)
