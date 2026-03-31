package com.tadeujeronimo.deliveryapp.data.repositories

import com.tadeujeronimo.deliveryapp.data.local.datasource.AddressLocalDataSource
import com.tadeujeronimo.deliveryapp.data.local.entity.AddressEntity
import com.tadeujeronimo.deliveryapp.data.models.User
import retrofit2.Response
import javax.inject.Inject

class AddressRepository @Inject constructor(private val addressLocalDataSource: AddressLocalDataSource) {

    suspend fun insertNewAddress(address: String) {
        addressLocalDataSource.insertNewAddress(address = address)
    }

    suspend fun getAllAddress(): List<AddressEntity> {
        return addressLocalDataSource.getAllAddress()
    }

    suspend fun updateAddressSelected(address: String) {
        addressLocalDataSource.updateAddressSelected(address = address)
    }
}
