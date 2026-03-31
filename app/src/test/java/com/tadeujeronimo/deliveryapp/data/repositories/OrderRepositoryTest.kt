package com.tadeujeronimo.deliveryapp.data.repositories

import com.tadeujeronimo.deliveryapp.data.TestDataFactory
import com.tadeujeronimo.deliveryapp.data.local.datasource.OrderLocalDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class OrderRepositoryTest {

    private val local = mockk<OrderLocalDataSource>()
    private val repository = OrderRepository(local)

    @Test
    fun insertNewOrder_shouldDelegateToLocalDataSource() = runTest {
        coEvery { local.insertNewOrder("100.0") } returns Unit

        repository.insertNewOrder("100.0")

        coVerify(exactly = 1) { local.insertNewOrder("100.0") }
    }

    @Test
    fun getAllOrders_shouldDelegateToLocalDataSource() = runTest {
        val expected = listOf(TestDataFactory.orderEntity())
        coEvery { local.getAllOrders() } returns expected

        val result = repository.getAllOrders()

        assertEquals(expected, result)
        coVerify(exactly = 1) { local.getAllOrders() }
    }
}
