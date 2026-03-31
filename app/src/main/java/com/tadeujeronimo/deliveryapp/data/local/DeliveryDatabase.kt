package com.tadeujeronimo.deliveryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tadeujeronimo.deliveryapp.data.local.dao.AddressDao
import com.tadeujeronimo.deliveryapp.data.local.dao.OrderDao
import com.tadeujeronimo.deliveryapp.data.local.dao.ProductFavoriteDao
import com.tadeujeronimo.deliveryapp.data.local.entity.AddressEntity
import com.tadeujeronimo.deliveryapp.data.local.entity.OrderEntity
import com.tadeujeronimo.deliveryapp.data.local.entity.ProductFavoriteEntity
import com.tadeujeronimo.deliveryapp.data.util.Converter

@Database(
    entities = [ProductFavoriteEntity::class, AddressEntity::class, OrderEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class DeliveryDatabase : RoomDatabase() {
    abstract fun productFavoriteDao(): ProductFavoriteDao
    abstract fun addressDao(): AddressDao
    abstract fun orderDao(): OrderDao
}