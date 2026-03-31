package com.tadeujeronimo.deliveryapp.data.models

import com.google.gson.annotations.SerializedName

data class CartProduct(
    @SerializedName("produtos")
    val products: List<Product>,
    @SerializedName("precoTotal")
    val totalPrice: Double,
    @SerializedName("frete")
    val freight: Double
) {
    fun returnTotalPrice(): Double {
        return totalPrice
    }
}
