package com.tadeujeronimo.deliveryapp.data.local.datasource

import com.tadeujeronimo.deliveryapp.data.local.dao.OrderDao
import com.tadeujeronimo.deliveryapp.data.local.entity.OrderEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class OrderLocalDataSourceTest {

    private val dao = mockk<OrderDao>()
    private val dataSource = OrderLocalDataSource(dao)

    @Test
    fun insertNewOrder_shouldInsertOrderEntity() = runTest {
        coEvery { dao.insertNewOrder(any()) } returns Unit

        dataSource.insertNewOrder("100.0")

        coVerify(exactly = 1) { dao.insertNewOrder(match { it.totalValueOrder == "100.0" }) }
    }

    @Test
    fun getAllOrders_shouldReturnDaoValues() = runTest {
        val expected = listOf(OrderEntity(1, "100.0"))
        coEvery { dao.allOrders() } returns expected

        val result = dataSource.getAllOrders()

        assertEquals(expected, result)
        coVerify(exactly = 1) { dao.allOrders() }
    }
}
