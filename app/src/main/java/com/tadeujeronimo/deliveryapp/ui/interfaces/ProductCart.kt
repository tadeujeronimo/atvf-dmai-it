package com.tadeujeronimo.deliveryapp.ui.interfaces

import com.tadeujeronimo.deliveryapp.data.models.Product

interface ProductCart {
    fun deleteProduct(product: Product)
    fun incrementProduct(product: Product)
    fun decrementProduct(product: Product)
}