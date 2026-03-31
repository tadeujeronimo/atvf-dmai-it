package com.tadeujeronimo.deliveryapp.data.remote

import com.tadeujeronimo.deliveryapp.data.TestDataFactory
import com.tadeujeronimo.deliveryapp.data.service.ProductService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class ProductRemoteDataSourceTest {

    private val productService = mockk<ProductService>()
    private val dataSource = ProductRemoteDataSource(productService)

    @Test
    fun getProduct_shouldReturnServiceResponse() = runTest {
        val expected = Response.success(listOf(TestDataFactory.product()))
        coEvery { productService.getProducts() } returns expected

        val result = dataSource.getProduct()

        assertEquals(expected, result)
        coVerify(exactly = 1) { productService.getProducts() }
    }

    @Test
    fun createProduct_shouldDelegateToService() = runTest {
        val product = TestDataFactory.product()
        val expected = Response.success(product)
        coEvery { productService.createProduct(product) } returns expected

        val result = dataSource.createProduct(product)

        assertEquals(expected, result)
        coVerify(exactly = 1) { productService.createProduct(product) }
    }

    @Test
    fun updateProduct_shouldUseProductId() = runTest {
        val product = TestDataFactory.product(id = "p1")
        val expected = Response.success(product)
        coEvery { productService.updateProduct("p1", product) } returns expected

        val result = dataSource.updateProduct(product)

        assertEquals(expected, result)
        coVerify(exactly = 1) { productService.updateProduct("p1", product) }
    }

    @Test
    fun deleteProduct_shouldDelegateToService() = runTest {
        val product = TestDataFactory.product()
        val expected = Response.success(product)
        coEvery { productService.deleteProduct("p1") } returns expected

        val result = dataSource.deleteProduct("p1")

        assertEquals(expected, result)
        coVerify(exactly = 1) { productService.deleteProduct("p1") }
    }
}
