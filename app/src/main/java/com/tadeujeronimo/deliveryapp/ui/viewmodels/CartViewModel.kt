package com.tadeujeronimo.deliveryapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.tadeujeronimo.deliveryapp.data.Resource
import com.tadeujeronimo.deliveryapp.data.models.CartProduct
import com.tadeujeronimo.deliveryapp.data.models.CartResponse
import com.tadeujeronimo.deliveryapp.data.models.Product
import com.tadeujeronimo.deliveryapp.data.repositories.CartRepository
import com.tadeujeronimo.deliveryapp.data.repositories.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _product: MutableLiveData<MutableList<Product>> =
        MutableLiveData<MutableList<Product>>().apply { value = mutableListOf() }

    val product: LiveData<MutableList<Product>> = _product as LiveData<MutableList<Product>>


    fun setProduct(product: Product) {
        _product.value?.let {
            val contains = it.filter { prod -> prod.name == product.name }

            if (contains.isNotEmpty()) {
                it.remove(product)
                product.quantity++
                product.priceUnit = product.priceUnit * product.quantity
            }

            it.add(product)
        }

    }

    fun createProduct(createCart: CartProduct): LiveData<Resource<CartResponse>> = liveData {
        emit(Resource.loading())

        try {
            val result = cartRepository.createCart(createCart)

            if (result.isSuccessful && result.body() != null) {
                emit(Resource(Resource.Status.SUCCESS, result.body()))
            } else {
                emit(Resource(Resource.Status.ERROR))
            }
        } catch (e: Exception) {
            Log.i("Exception", "Exception no make login ${e.toString()}")
            emit(Resource(Resource.Status.ERROR))
        }
    }

    fun getCart(idCart: String): LiveData<Resource<CartResponse>> = liveData {
        emit(Resource.loading())

        try {
            val result = cartRepository.getCart(idCart)

            if (result.isSuccessful && result.body() != null) {
                _product.value?.clear()
                _product.value?.addAll(result.body()!!.products)
                emit(Resource(Resource.Status.SUCCESS, result.body()))
            } else {
                emit(Resource(Resource.Status.ERROR))
            }
        } catch (e: Exception) {
            Log.i("Exception", "Exception no make login ${e.toString()}")
            emit(Resource(Resource.Status.ERROR))
        }
    }

    fun updateCart(idCart: String, cartProduct: CartProduct): LiveData<Resource<CartResponse>> =
        liveData {
            emit(Resource.loading())

            try {
                val result = cartRepository.updateCart(idCart, cartProduct)

                if (result.isSuccessful && result.body() != null) {
                    emit(Resource(Resource.Status.SUCCESS, result.body()))
                } else {
                    emit(Resource(Resource.Status.ERROR))
                }
            } catch (e: Exception) {
                Log.i("Exception", "Exception no make login ${e.toString()}")
                emit(Resource(Resource.Status.ERROR))
            }
        }

    fun getTotalPrice(): Double {
        var totalPrice = 0.0

        _product.value?.forEach {
            totalPrice += it.priceUnit
        }

        if (totalPrice <= 0) {
            totalPrice = 0.1
            return totalPrice
        }

        return totalPrice
    }

    fun deleteProduct(product: Product): List<Product> {
        val productBefore = _product.value?.find { it.name == product.name }
        _product.value?.remove(productBefore)

        return _product.value as List<Product>
    }

    fun incrementQuantity(product: Product): List<Product> {
        val productBefore = _product.value?.find { it.name == product.name }
        _product.value?.remove(productBefore)

        val priceUnitProduct = product.priceUnit / product.quantity

        product.quantity++
        product.priceUnit += priceUnitProduct
        _product.value?.add(product)

        return _product.value as List<Product>
    }

    fun decrementQuantity(product: Product): List<Product> {
        val productBefore = _product.value?.find { it.name == product.name }

        if (productBefore!!.quantity <= 1) {
            _product.value?.remove(productBefore)
        } else {
            _product.value?.remove(productBefore)

            val priceUnitProduct = product.priceUnit / product.quantity

            product.quantity--
            product.priceUnit -= priceUnitProduct
            _product.value?.add(product)
        }

        return _product.value as List<Product>
    }

    fun insertOrder(totalPrice: String) {
        viewModelScope.launch {
            orderRepository.insertNewOrder(totalPrice)
        }
    }

}