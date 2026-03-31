package com.tadeujeronimo.deliveryapp.data.remote

import com.tadeujeronimo.deliveryapp.data.models.Product
import com.tadeujeronimo.deliveryapp.data.service.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductRemoteDataSource constructor(private val productService: ProductService) {

    suspend fun getProduct(): Response<List<Product>> {
        var result: Response<List<Product>>

        withContext(Dispatchers.IO) {
            result = productService.getProducts()
        }

        return result
    }

    suspend fun createProduct(product: Product): Response<Product> {
        val result: Response<Product>

        withContext(Dispatchers.IO) {
            result = productService.createProduct(product)
        }

        return result
    }

    suspend fun updateProduct(product: Product): Response<Product> {
        val result: Response<Product>

        withContext(Dispatchers.IO) {
            result = productService.updateProduct(product.productId, product)
        }

        return result
    }

    suspend fun deleteProduct(productId: String): Response<Product> {
        val result: Response<Product>

        withContext(Dispatchers.IO) {
            result = productService.deleteProduct(productId)
        }

        return result
    }
}