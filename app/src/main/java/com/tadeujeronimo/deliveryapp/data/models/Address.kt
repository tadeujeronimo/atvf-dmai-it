package com.tadeujeronimo.deliveryapp.data.models

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("rua")
    val street: String,
    @SerializedName("numero")
    val number: Int,
    @SerializedName("complemento")
    val complement: String,
    @SerializedName("CEP")
    val cep: String
)