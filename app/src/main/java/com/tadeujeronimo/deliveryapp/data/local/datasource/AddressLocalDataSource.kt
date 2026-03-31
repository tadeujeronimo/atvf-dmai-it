package com.tadeujeronimo.deliveryapp.data.local.datasource

import com.tadeujeronimo.deliveryapp.data.local.dao.AddressDao
import com.tadeujeronimo.deliveryapp.data.local.entity.AddressEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddressLocalDataSource constructor(private val addressDao: AddressDao) {

    suspend fun insertNewAddress(address: String) {
        val addressEntity = AddressEntity(address = address, selected = true)

        withContext(Dispatchers.IO) {
            addressDao.allSelectedAddressForFalse()
            addressDao.insertNewAddress(addressEntity)
        }
    }

    suspend fun getAllAddress(): List<AddressEntity> {
        var result: List<AddressEntity> = listOf()

        withContext(Dispatchers.IO) {
            result = addressDao.allAddress()
        }

        return result
    }

    suspend fun updateAddressSelected(address: String) {
        withContext(Dispatchers.IO) {
            addressDao.allSelectedAddressForFalse()
            addressDao.upadateAddressSelected(address)
        }
    }
}