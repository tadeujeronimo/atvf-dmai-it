package com.tadeujeronimo.deliveryapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tadeujeronimo.deliveryapp.data.local.entity.OrderEntity
import com.tadeujeronimo.deliveryapp.data.repositories.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val orderRepository: OrderRepository) :
    ViewModel() {

    fun getAllOrders() = liveData {
        var result: List<OrderEntity> = listOf()

        result = orderRepository.getAllOrders()

        emit(result)
    }
}