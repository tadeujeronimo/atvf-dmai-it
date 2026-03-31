package com.tadeujeronimo.deliveryapp.data.remote

import com.tadeujeronimo.deliveryapp.data.TestDataFactory
import com.tadeujeronimo.deliveryapp.data.service.CartService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class CartRemoteDataSourceTest {

    private val cartService = mockk<CartService>()
    private val dataSource = CartRemoteDataSource(cartService)

    @Test
    fun createCart_shouldReturnServiceResponse() = runTest {
        val cartProduct = TestDataFactory.cartProduct()
        val expected = Response.success(TestDataFactory.cartResponse())
        coEvery { cartService.createCart(cartProduct) } returns expected

        val result = dataSource.createCart(cartProduct)

        assertEquals(expected, result)
        coVerify(exactly = 1) { cartService.createCart(cartProduct) }
    }

    @Test
    fun getCart_shouldDelegateToService() = runTest {
        val expected = Response.success(TestDataFactory.cartResponse())
        coEvery { cartService.getCart("c1") } returns expected

        val result = dataSource.getCart("c1")

        assertEquals(expected, result)
        coVerify(exactly = 1) { cartService.getCart("c1") }
    }

    @Test
    fun updateCart_shouldDelegateToService() = runTest {
        val cartProduct = TestDataFactory.cartProduct()
        val expected = Response.success(TestDataFactory.cartResponse())
        coEvery { cartService.updateCart("c1", cartProduct) } returns expected

        val result = dataSource.updateCart("c1", cartProduct)

        assertEquals(expected, result)
        coVerify(exactly = 1) { cartService.updateCart("c1", cartProduct) }
    }
}
