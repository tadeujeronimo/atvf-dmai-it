package com.tadeujeronimo.deliveryapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "productsFavorite"
)
data class ProductFavoriteEntity(
    @PrimaryKey val productId: String,
    @ColumnInfo(name = "product_name") val productName: String,
    @ColumnInfo(name = "product_image") val image: String,
    @ColumnInfo(name = "product_price") val productPrice: String,
)
