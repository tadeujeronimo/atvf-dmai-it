package com.tadeujeronimo.deliveryapp.data.local.datasource

import com.tadeujeronimo.deliveryapp.data.local.dao.ProductFavoriteDao
import com.tadeujeronimo.deliveryapp.data.local.entity.ProductFavoriteEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductFavoriteLocalDataSourceTest {

    private val dao = mockk<ProductFavoriteDao>()
    private val dataSource = ProductFavoriteLocalDataSource(dao)

    @Test
    fun getAllProductsFavorite_shouldReturnDaoValues() = runTest {
        val expected = listOf(ProductFavoriteEntity("p1", "Pizza", "img", "20.0"))
        coEvery { dao.getAllProductsFavorite() } returns expected

        val result = dataSource.getAllProductsFavorite()

        assertEquals(expected, result)
        coVerify(exactly = 1) { dao.getAllProductsFavorite() }
    }

    @Test
    fun insertProductFavorite_shouldDelegateToDao() = runTest {
        val entity = ProductFavoriteEntity("p1", "Pizza", "img", "20.0")
        coEvery { dao.insertProductFavorite(entity) } returns Unit

        dataSource.insertProductFavorite(entity)

        coVerify(exactly = 1) { dao.insertProductFavorite(entity) }
    }

    @Test
    fun deleteProductFavorite_shouldDelegateToDao() = runTest {
        val entity = ProductFavoriteEntity("p1", "Pizza", "img", "20.0")
        coEvery { dao.deleteProductFavorite(entity) } returns Unit

        dataSource.deleteProductFavorite(entity)

        coVerify(exactly = 1) { dao.deleteProductFavorite(entity) }
    }
}
