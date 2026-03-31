package com.tadeujeronimo.deliveryapp.data.repositories

import com.tadeujeronimo.deliveryapp.data.TestDataFactory
import com.tadeujeronimo.deliveryapp.data.local.datasource.AddressLocalDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class AddressRepositoryTest {

    private val local = mockk<AddressLocalDataSource>()
    private val repository = AddressRepository(local)

    @Test
    fun insertNewAddress_shouldDelegateToLocalDataSource() = runTest {
        coEvery { local.insertNewAddress("Rua A") } returns Unit

        repository.insertNewAddress("Rua A")

        coVerify(exactly = 1) { local.insertNewAddress("Rua A") }
    }

    @Test
    fun getAllAddress_shouldDelegateToLocalDataSource() = runTest {
        val expected = listOf(TestDataFactory.addressEntity())
        coEvery { local.getAllAddress() } returns expected

        val result = repository.getAllAddress()

        assertEquals(expected, result)
        coVerify(exactly = 1) { local.getAllAddress() }
    }

    @Test
    fun updateAddressSelected_shouldDelegateToLocalDataSource() = runTest {
        coEvery { local.updateAddressSelected("Rua A") } returns Unit

        repository.updateAddressSelected("Rua A")

        coVerify(exactly = 1) { local.updateAddressSelected("Rua A") }
    }
}
