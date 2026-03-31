package com.tadeujeronimo.deliveryapp.data.repositories

import com.tadeujeronimo.deliveryapp.data.TestDataFactory
import com.tadeujeronimo.deliveryapp.data.local.datasource.ProductFavoriteLocalDataSource
import com.tadeujeronimo.deliveryapp.data.local.entity.ProductFavoriteEntity
import com.tadeujeronimo.deliveryapp.data.models.Product
import com.tadeujeronimo.deliveryapp.data.remote.ProductRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class ProductRepositoryTest {

    private val remote = mockk<ProductRemoteDataSource>()
    private val local = mockk<ProductFavoriteLocalDataSource>()
    private val repository = ProductRepository(remote, local)

    @Test
    fun getProducts_shouldDelegateToRemoteDataSource() = runTest {
        val expected = Response.success(listOf(TestDataFactory.product()))
        coEvery { remote.getProduct() } returns expected

        val result = repository.getProducts()

        assertEquals(expected, result)
        coVerify(exactly = 1) { remote.getProduct() }
    }

    @Test
    fun createProduct_shouldDelegateToRemoteDataSource() = runTest {
        val product = TestDataFactory.product()
        val expected = Response.success(product)
        coEvery { remote.createProduct(product) } returns expected

        val result = repository.createProduct(product)

        assertEquals(expected, result)
        coVerify(exactly = 1) { remote.createProduct(product) }
    }

    @Test
    fun updateProduct_shouldDelegateToRemoteDataSource() = runTest {
        val product = TestDataFactory.product()
        val expected = Response.success(product)
        coEvery { remote.updateProduct(product) } returns expected

        val result = repository.updateProduct(product)

        assertEquals(expected, result)
        coVerify(exactly = 1) { remote.updateProduct(product) }
    }

    @Test
    fun deleteProduct_shouldDelegateToRemoteDataSource() = runTest {
        val product = TestDataFactory.product()
        val expected = Response.success(product)
        coEvery { remote.deleteProduct("p1") } returns expected

        val result = repository.deleteProduct("p1")

        assertEquals(expected, result)
        coVerify(exactly = 1) { remote.deleteProduct("p1") }
    }

    @Test
    fun getAllProducts_shouldMapEntityToDomain() = runTest {
        val entities = listOf(
            ProductFavoriteEntity("p1", "Produto", "img", "22.9")
        )
        coEvery { local.getAllProductsFavorite() } returns entities

        val result = repository.getAllProducts()

        assertEquals(1, result.size)
        assertEquals("p1", result[0].productId)
        assertEquals("Produto", result[0].name)
        assertEquals(22.9, result[0].priceUnit, 0.0)
    }

    @Test
    fun insertProductFavorite_shouldUseProductIdWhenPresent() = runTest {
        val product = TestDataFactory.product(id = "p1", code = 999)
        coEvery { local.insertProductFavorite(any()) } returns Unit

        repository.insertProductFavorite(product)

        coVerify(exactly = 1) {
            local.insertProductFavorite(match { it.productId == "p1" && it.productName == product.name })
        }
    }

    @Test
    fun insertProductFavorite_shouldFallbackToCodeWhenIdIsNull() = runTest {
        val product = Product(
            productId = null,
            name = "Produto",
            description = "Desc",
            image = "img",
            priceUnit = 11.0,
            code = 777
        )
        coEvery { local.insertProductFavorite(any()) } returns Unit

        repository.insertProductFavorite(product)

        coVerify(exactly = 1) {
            local.insertProductFavorite(match { it.productId == "777" })
        }
    }

    @Test
    fun deleteProductFavorite_shouldDelegateToLocalDataSource() = runTest {
        val product = TestDataFactory.product(id = "p1")
        coEvery { local.deleteProductFavorite(any()) } returns Unit

        repository.deleteProductFavorite(product)

        coVerify(exactly = 1) {
            local.deleteProductFavorite(match { it.productId == "p1" })
        }
    }
}
