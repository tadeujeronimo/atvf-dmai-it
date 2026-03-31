package com.tadeujeronimo.deliveryapp.data.repositories

import com.tadeujeronimo.deliveryapp.data.TestDataFactory
import com.tadeujeronimo.deliveryapp.data.remote.UserRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class UserRepositoryTest {

    private val remote = mockk<UserRemoteDataSource>()
    private val repository = UserRepository(remote)

    @Test
    fun getUserById_shouldDelegateToRemoteDataSource() = runTest {
        val expected = Response.success(TestDataFactory.user())
        coEvery { remote.getUserById("u1") } returns expected

        val result = repository.getUserById("u1")

        assertEquals(expected, result)
        coVerify(exactly = 1) { remote.getUserById("u1") }
    }

    @Test
    fun getUsers_shouldDelegateToRemoteDataSource() = runTest {
        val expected = Response.success(listOf(TestDataFactory.user()))
        coEvery { remote.getUsers() } returns expected

        val result = repository.getUsers()

        assertEquals(expected, result)
        coVerify(exactly = 1) { remote.getUsers() }
    }

    @Test
    fun createUser_shouldDelegateToRemoteDataSource() = runTest {
        val user = TestDataFactory.user()
        val expected = Response.success(user)
        coEvery { remote.createUser(user) } returns expected

        val result = repository.createUser(user)

        assertEquals(expected, result)
        coVerify(exactly = 1) { remote.createUser(user) }
    }

    @Test
    fun updateUser_shouldDelegateToRemoteDataSource() = runTest {
        val user = TestDataFactory.user()
        val expected = Response.success(user)
        coEvery { remote.updateUser("u1", user) } returns expected

        val result = repository.updateUser("u1", user)

        assertEquals(expected, result)
        coVerify(exactly = 1) { remote.updateUser("u1", user) }
    }

    @Test
    fun deleteUser_shouldDelegateToRemoteDataSource() = runTest {
        val expected = Response.success(mapOf("message" to "ok"))
        coEvery { remote.deleteUser("u1") } returns expected

        val result = repository.deleteUser("u1")

        assertEquals(expected, result)
        coVerify(exactly = 1) { remote.deleteUser("u1") }
    }
}
