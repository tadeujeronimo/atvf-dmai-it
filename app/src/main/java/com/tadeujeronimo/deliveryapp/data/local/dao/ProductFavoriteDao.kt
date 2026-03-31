package com.tadeujeronimo.deliveryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tadeujeronimo.deliveryapp.data.local.entity.ProductFavoriteEntity

@Dao
interface ProductFavoriteDao {
    @Query("SELECT * FROM productsFavorite")
    suspend fun getAllProductsFavorite(): List<ProductFavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductFavorite(entity: ProductFavoriteEntity)

    @Delete
    suspend fun deleteProductFavorite(entity: ProductFavoriteEntity)
}