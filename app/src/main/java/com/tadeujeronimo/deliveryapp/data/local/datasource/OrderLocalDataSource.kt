package com.tadeujeronimo.deliveryapp.data.local.datasource

import com.tadeujeronimo.deliveryapp.data.local.dao.OrderDao
import com.tadeujeronimo.deliveryapp.data.local.entity.OrderEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrderLocalDataSource constructor(private val orderDao: OrderDao) {

    suspend fun insertNewOrder(totalValue: String) {
        val order = OrderEntity(totalValueOrder = totalValue)

        withContext(Dispatchers.IO) {
            orderDao.insertNewOrder(order)
        }
    }

    suspend fun getAllOrders(): List<OrderEntity> {
        var result: List<OrderEntity> = listOf()

        withContext(Dispatchers.IO) {
            result = orderDao.allOrders()
        }

        return result
    }
}