package com.tadeujeronimo.deliveryapp.data.repositories

import com.tadeujeronimo.deliveryapp.data.local.datasource.OrderLocalDataSource
import com.tadeujeronimo.deliveryapp.data.local.entity.AddressEntity
import com.tadeujeronimo.deliveryapp.data.local.entity.OrderEntity
import javax.inject.Inject

class OrderRepository @Inject constructor(private val orderLocalDataSource: OrderLocalDataSource) {

    suspend fun insertNewOrder(totalValue: String) {
        orderLocalDataSource.insertNewOrder(totalValue)
    }

    suspend fun getAllOrders(): List<OrderEntity> {
        return orderLocalDataSource.getAllOrders()
    }
}
