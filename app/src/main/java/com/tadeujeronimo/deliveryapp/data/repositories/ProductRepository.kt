package com.tadeujeronimo.deliveryapp.data.repositories

import com.tadeujeronimo.deliveryapp.data.local.datasource.ProductFavoriteLocalDataSource
import com.tadeujeronimo.deliveryapp.data.local.entity.ProductFavoriteEntity
import com.tadeujeronimo.deliveryapp.data.models.Product
import com.tadeujeronimo.deliveryapp.data.models.ProductFavorite
import com.tadeujeronimo.deliveryapp.data.remote.ProductRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productFavoriteLocalDataSource: ProductFavoriteLocalDataSource
) {

    suspend fun getProducts(): Response<List<Product>> {
        return productRemoteDataSource.getProduct()
    }

    suspend fun createProduct(product: Product): Response<Product> {
        return productRemoteDataSource.createProduct(product)
    }

    suspend fun updateProduct(product: Product): Response<Product> {
        return productRemoteDataSource.updateProduct(product)
    }

    suspend fun deleteProduct(productId: String): Response<Product> {
        return productRemoteDataSource.deleteProduct(productId)
    }

    suspend fun getAllProducts(): List<ProductFavorite> {
        val result = productFavoriteLocalDataSource.getAllProductsFavorite()

        val listOfProducts = mutableListOf<ProductFavorite>()

        result.forEach { product ->
            val productFavorite = ProductFavorite(
                product.productId,
                product.productName,
                product.image,
                product.productPrice.toDouble()
            )

            listOfProducts.add(productFavorite)
        }

        return listOfProducts
    }

    suspend fun insertProductFavorite(product: Product) {
        val localId = product.productId ?: product.code.toString()

        val productEntity = ProductFavoriteEntity(
            localId,
            product.name,
            product.image,
            product.priceUnit.toString()
        )

        productFavoriteLocalDataSource.insertProductFavorite(productEntity)
    }

    suspend fun deleteProductFavorite(product: Product) {
        val localId = product.productId ?: product.code.toString()

        val productEntity = ProductFavoriteEntity(
            localId,
            product.name,
            product.image,
            product.priceUnit.toString()
        )

        productFavoriteLocalDataSource.deleteProductFavorite(productEntity)
    }
}