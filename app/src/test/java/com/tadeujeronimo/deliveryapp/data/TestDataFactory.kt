package com.tadeujeronimo.deliveryapp.data

import com.tadeujeronimo.deliveryapp.data.local.entity.AddressEntity
import com.tadeujeronimo.deliveryapp.data.local.entity.OrderEntity
import com.tadeujeronimo.deliveryapp.data.local.entity.ProductFavoriteEntity
import com.tadeujeronimo.deliveryapp.data.models.Address
import com.tadeujeronimo.deliveryapp.data.models.CartProduct
import com.tadeujeronimo.deliveryapp.data.models.CartResponse
import com.tadeujeronimo.deliveryapp.data.models.Product
import com.tadeujeronimo.deliveryapp.data.models.User

object TestDataFactory {
    fun product(
        id: String = "product-id",
        name: String = "Pizza",
        price: Double = 10.0,
        code: Int = 123
    ) = Product(
        productId = id,
        name = name,
        description = "Desc",
        image = "img",
        priceUnit = price,
        code = code
    )

    fun user(id: String = "user-id", email: String = "user@email.com") = User(
        userId = id,
        name = "User",
        email = email,
        password = "123",
        image = "img",
        isAdmin = false,
        address = listOf(Address("Rua", 1, "Comp", "00000-000"))
    )

    fun cartProduct() = CartProduct(
        products = listOf(product()),
        totalPrice = 10.0,
        freight = 5.0
    )

    fun cartResponse() = CartResponse(
        cartId = "cart-id",
        userId = "user-id",
        products = listOf(product()),
        totalPrice = 10.0,
        freight = 5.0
    )

    fun addressEntity(address: String = "Rua A", selected: Boolean = true) = AddressEntity(
        id = 1,
        address = address,
        selected = selected
    )

    fun orderEntity(total: String = "100.0") = OrderEntity(
        id = 1,
        totalValueOrder = total
    )

    fun productFavoriteEntity(id: String = "product-id") = ProductFavoriteEntity(
        productId = id,
        productName = "Pizza",
        image = "img",
        productPrice = "10.0"
    )
}
