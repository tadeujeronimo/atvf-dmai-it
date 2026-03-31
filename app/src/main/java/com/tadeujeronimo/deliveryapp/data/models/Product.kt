package com.tadeujeronimo.deliveryapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @SerializedName("_id")
    var productId: String? = null,
    @SerializedName("nome")
    val name: String,
    @SerializedName("descricao")
    val description: String,
    @SerializedName("imagem")
    val image: String,
    @SerializedName("precoUnitario")
    var priceUnit: Double,
    @SerializedName("codigoBarra")
    val code: Int,
    @SerializedName("quantidade")
    var quantity: Int = 1,
    var isFavorite: Boolean = false
) : Parcelable