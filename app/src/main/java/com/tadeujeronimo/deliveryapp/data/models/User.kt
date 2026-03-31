package com.tadeujeronimo.deliveryapp.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id")
    val userId: String? = null,
    @SerializedName("nome")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("senha")
    val password: String,
    @SerializedName("imagem")
    val image: String,
    @SerializedName("admin")
    var isAdmin: Boolean = false,
    @SerializedName("enderecos")
    val address: List<Address>,
    @SerializedName("produtos_fav")
    val favoriteProducts: List<FavoriteProductRef> = listOf(),
)