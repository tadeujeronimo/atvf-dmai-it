package com.tadeujeronimo.deliveryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tadeujeronimo.deliveryapp.data.local.entity.AddressEntity

@Dao
interface AddressDao {
    @Query("UPDATE address SET selected = 0 where selected = 1")
    suspend fun allSelectedAddressForFalse()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewAddress(addressEntity: AddressEntity)

    @Query("SELECT * FROM address order by selected = 1")
    suspend fun allAddress(): List<AddressEntity>

    @Query("UPDATE address SET selected = 1 where address = :addressName")
    suspend fun upadateAddressSelected(addressName: String)
}