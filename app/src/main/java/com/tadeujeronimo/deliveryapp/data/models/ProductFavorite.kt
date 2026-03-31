package com.tadeujeronimo.deliveryapp.data.models

import com.google.gson.annotations.SerializedName

data class ProductFavorite(
    @SerializedName("_id")
    val productId: String? = null,
    @SerializedName("nome")
    val name: String,
    @SerializedName("imagem")
    val image: String,
    @SerializedName("precoUnitario")
    var priceUnit: Double,
)