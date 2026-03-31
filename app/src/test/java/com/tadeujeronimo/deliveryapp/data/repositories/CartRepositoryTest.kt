package com.tadeujeronimo.deliveryapp.data.repositories

import com.tadeujeronimo.deliveryapp.data.TestDataFactory
import com.tadeujeronimo.deliveryapp.data.remote.CartRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class CartRepositoryTest {

    private val remote = mockk<CartRemoteDataSource>()
    private val repository = CartRepository(remote)

    @Test
    fun createCart_shouldDelegateToRemoteDataSource() = runTest {
        val request = TestDataFactory.cartProduct()
        val expected = Response.success(TestDataFactory.cartResponse())
        coEvery { remote.createCart(request) } returns expected

        val result = repository.createCart(request)

        assertEquals(expected, result)
        coVerify(exactly = 1) { remote.createCart(request) }
    }

    @Test
    fun getCart_shouldDelegateToRemoteDataSource() = runTest {
        val expected = Response.success(TestDataFactory.cartResponse())
        coEvery { remote.getCart("c1") } returns expected

        val result = repository.getCart("c1")

        assertEquals(expected, result)
        coVerify(exactly = 1) { remote.getCart("c1") }
    }

    @Test
    fun updateCart_shouldDelegateToRemoteDataSource() = runTest {
        val request = TestDataFactory.cartProduct()
        val expected = Response.success(TestDataFactory.cartResponse())
        coEvery { remote.updateCart("c1", request) } returns expected

        val result = repository.updateCart("c1", request)

        assertEquals(expected, result)
        coVerify(exactly = 1) { remote.updateCart("c1", request) }
    }
}
