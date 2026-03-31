package com.tadeujeronimo.deliveryapp.data.local.datasource

import com.tadeujeronimo.deliveryapp.data.local.dao.AddressDao
import com.tadeujeronimo.deliveryapp.data.local.entity.AddressEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class AddressLocalDataSourceTest {

    private val dao = mockk<AddressDao>()
    private val dataSource = AddressLocalDataSource(dao)

    @Test
    fun insertNewAddress_shouldResetSelectedAndInsert() = runTest {
        coEvery { dao.allSelectedAddressForFalse() } returns Unit
        coEvery { dao.insertNewAddress(any()) } returns Unit

        dataSource.insertNewAddress("Rua A")

        coVerify(exactly = 1) { dao.allSelectedAddressForFalse() }
        coVerify(exactly = 1) { dao.insertNewAddress(match { it.address == "Rua A" && it.selected }) }
    }

    @Test
    fun getAllAddress_shouldReturnDaoValues() = runTest {
        val expected = listOf(AddressEntity(1, "Rua A", true))
        coEvery { dao.allAddress() } returns expected

        val result = dataSource.getAllAddress()

        assertEquals(expected, result)
        coVerify(exactly = 1) { dao.allAddress() }
    }

    @Test
    fun updateAddressSelected_shouldResetAndSelectAddress() = runTest {
        coEvery { dao.allSelectedAddressForFalse() } returns Unit
        coEvery { dao.upadateAddressSelected("Rua A") } returns Unit

        dataSource.updateAddressSelected("Rua A")

        coVerify(exactly = 1) { dao.allSelectedAddressForFalse() }
        coVerify(exactly = 1) { dao.upadateAddressSelected("Rua A") }
    }
}
