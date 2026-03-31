package com.tadeujeronimo.deliveryapp.data.models

import com.google.gson.annotations.SerializedName

data class FavoriteProductRef(
    @SerializedName("_id")
    val productId: String? = null
)