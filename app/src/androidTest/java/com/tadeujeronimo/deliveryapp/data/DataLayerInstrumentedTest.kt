package com.tadeujeronimo.deliveryapp.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tadeujeronimo.deliveryapp.data.local.dao.ProductFavoriteDao
import com.tadeujeronimo.deliveryapp.data.local.datasource.ProductFavoriteLocalDataSource
import com.tadeujeronimo.deliveryapp.data.local.entity.ProductFavoriteEntity
import com.tadeujeronimo.deliveryapp.data.models.Address
import com.tadeujeronimo.deliveryapp.data.models.CartProduct
import com.tadeujeronimo.deliveryapp.data.models.CartResponse
import com.tadeujeronimo.deliveryapp.data.models.Product
import com.tadeujeronimo.deliveryapp.data.models.User
import com.tadeujeronimo.deliveryapp.data.remote.CartRemoteDataSource
import com.tadeujeronimo.deliveryapp.data.remote.ProductRemoteDataSource
import com.tadeujeronimo.deliveryapp.data.remote.UserRemoteDataSource
import com.tadeujeronimo.deliveryapp.data.repositories.CartRepository
import com.tadeujeronimo.deliveryapp.data.repositories.ProductRepository
import com.tadeujeronimo.deliveryapp.data.repositories.UserRepository
import com.tadeujeronimo.deliveryapp.data.service.CartService
import com.tadeujeronimo.deliveryapp.data.service.ProductService
import com.tadeujeronimo.deliveryapp.data.service.UserService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class DataLayerInstrumentedTest {

    @Test
    fun productRemoteDataSource_shouldReturnServiceProducts() = runBlocking {
        val products = listOf(sampleProduct(id = "p1"))
        val service = FakeProductService(products)
        val dataSource = ProductRemoteDataSource(service)

        val result = dataSource.getProduct()

        assertTrue(result.isSuccessful)
        assertEquals(products, result.body())
        assertTrue(service.getProductsCalled)
    }

    @Test
    fun cartRepository_shouldDelegateToRemoteDataSource() = runBlocking {
        val cartResponse = sampleCartResponse()
        val service = FakeCartService(cartResponse)
        val dataSource = CartRemoteDataSource(service)
        val repository = CartRepository(dataSource)

        val result = repository.updateCart("c1", sampleCartProduct())

        assertTrue(result.isSuccessful)
        assertEquals(cartResponse, result.body())
        assertEquals("c1", service.lastUpdatedCartId)
    }

    @Test
    fun userRepository_shouldDelegateToRemoteDataSource() = runBlocking {
        val user = sampleUser(id = "u1")
        val service = FakeUserService(user)
        val dataSource = UserRemoteDataSource(service)
        val repository = UserRepository(dataSource)

        val result = repository.getUserById("u1")

        assertTrue(result.isSuccessful)
        assertEquals(user, result.body())
        assertEquals("u1", service.lastUserById)
    }

    @Test
    fun productRepository_shouldMapLocalFavoriteEntity() = runBlocking {
        val service = FakeProductService(listOf(sampleProduct()))
        val dao = FakeProductFavoriteDao(
            initial = mutableListOf(
                ProductFavoriteEntity("p1", "Pizza", "img", "22.9")
            )
        )
        val repository = ProductRepository(
            ProductRemoteDataSource(service),
            ProductFavoriteLocalDataSource(dao)
        )

        val result = repository.getAllProducts()

        assertEquals(1, result.size)
        assertEquals("p1", result[0].productId)
        assertEquals("Pizza", result[0].name)
        assertEquals(22.9, result[0].priceUnit, 0.0)
    }

    @Test
    fun productRepository_shouldUseCodeWhenProductIdIsNull() = runBlocking {
        val service = FakeProductService(listOf(sampleProduct()))
        val dao = FakeProductFavoriteDao(initial = mutableListOf())
        val repository = ProductRepository(
            ProductRemoteDataSource(service),
            ProductFavoriteLocalDataSource(dao)
        )

        val productWithoutId = sampleProduct(id = null, code = 777)
        repository.insertProductFavorite(productWithoutId)

        assertEquals("777", dao.lastInserted?.productId)
    }

    private fun sampleProduct(id: String? = "product-id", code: Int = 123) = Product(
        productId = id,
        name = "Pizza",
        description = "Descricao",
        image = "img",
        priceUnit = 10.0,
        code = code
    )

    private fun sampleCartProduct() = CartProduct(
        products = listOf(sampleProduct()),
        totalPrice = 10.0,
        freight = 5.0
    )

    private fun sampleCartResponse() = CartResponse(
        cartId = "cart-id",
        userId = "user-id",
        products = listOf(sampleProduct()),
        totalPrice = 10.0,
        freight = 5.0
    )

    private fun sampleUser(id: String = "user-id") = User(
        userId = id,
        name = "User",
        email = "user@email.com",
        password = "123",
        image = "img",
        isAdmin = false,
        address = listOf(Address("Rua", 1, "Comp", "00000-000"))
    )

    private class FakeProductService(
        products: List<Product>
    ) : ProductService {
        var getProductsCalled = false
        private val productsResponse = Response.success(products)

        override suspend fun getProducts(): Response<List<Product>> {
            getProductsCalled = true
            return productsResponse
        }

        override suspend fun createProduct(product: Product): Response<Product> = Response.success(product)

        override suspend fun updateProduct(idProduct: String?, product: Product): Response<Product> =
            Response.success(product)

        override suspend fun deleteProduct(idProduct: String?): Response<Product> =
            Response.success(
                Product(
                    productId = idProduct,
                    name = "Pizza",
                    description = "Descricao",
                    image = "img",
                    priceUnit = 10.0,
                    code = 123
                )
            )
    }

    private class FakeCartService(
        private val responseBody: CartResponse
    ) : CartService {
        var lastUpdatedCartId: String? = null

        override suspend fun createCart(cartCreate: CartProduct): Response<CartResponse> =
            Response.success(responseBody)

        override suspend fun getCart(idCart: String): Response<CartResponse> = Response.success(responseBody)

        override suspend fun updateCart(idCart: String, cartProduct: CartProduct): Response<CartResponse> {
            lastUpdatedCartId = idCart
            return Response.success(responseBody)
        }
    }

    private class FakeUserService(
        private val user: User
    ) : UserService {
        var lastUserById: String? = null

        override suspend fun getUserById(idUser: String): Response<User> {
            lastUserById = idUser
            return Response.success(user)
        }

        override suspend fun getUsers(limit: Int?, offset: Int?): Response<List<User>> =
            Response.success(listOf(user))

        override suspend fun createUser(user: User): Response<User> = Response.success(user)

        override suspend fun updateUser(idUser: String, user: User): Response<User> = Response.success(user)

        override suspend fun deleteUser(idUser: String): Response<Map<String, String>> =
            Response.success(mapOf("message" to "ok"))
    }

    private class FakeProductFavoriteDao(
        private val initial: MutableList<ProductFavoriteEntity>
    ) : ProductFavoriteDao {
        var lastInserted: ProductFavoriteEntity? = null

        override suspend fun getAllProductsFavorite(): List<ProductFavoriteEntity> = initial.toList()

        override suspend fun insertProductFavorite(entity: ProductFavoriteEntity) {
            lastInserted = entity
            initial.removeAll { it.productId == entity.productId }
            initial.add(entity)
        }

        override suspend fun deleteProductFavorite(entity: ProductFavoriteEntity) {
            initial.removeAll { it.productId == entity.productId }
        }
    }
}
