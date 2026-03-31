package com.tadeujeronimo.deliveryapp.data.remote

import com.tadeujeronimo.deliveryapp.data.TestDataFactory
import com.tadeujeronimo.deliveryapp.data.models.User
import com.tadeujeronimo.deliveryapp.data.service.UserService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class UserRemoteDataSourceTest {

    private val userService = mockk<UserService>()
    private val dataSource = UserRemoteDataSource(userService)

    @Test
    fun getUserById_shouldDelegateToService() = runTest {
        val user = TestDataFactory.user()
        val expected = Response.success(user)
        coEvery { userService.getUserById("u1") } returns expected

        val result = dataSource.getUserById("u1")

        assertEquals(expected, result)
        coVerify(exactly = 1) { userService.getUserById("u1") }
    }

    @Test
    fun getUsers_shouldDelegateToService() = runTest {
        val expected = Response.success(listOf(TestDataFactory.user()))
        coEvery { userService.getUsers() } returns expected

        val result = dataSource.getUsers()

        assertEquals(expected, result)
        coVerify(exactly = 1) { userService.getUsers() }
    }

    @Test
    fun createUser_shouldDelegateToService() = runTest {
        val user = TestDataFactory.user()
        val expected = Response.success(user)
        coEvery { userService.createUser(user) } returns expected

        val result = dataSource.createUser(user)

        assertEquals(expected, result)
        coVerify(exactly = 1) { userService.createUser(user) }
    }

    @Test
    fun updateUser_shouldDelegateToService() = runTest {
        val user = TestDataFactory.user()
        val expected = Response.success(user)
        coEvery { userService.updateUser("u1", user) } returns expected

        val result = dataSource.updateUser("u1", user)

        assertEquals(expected, result)
        coVerify(exactly = 1) { userService.updateUser("u1", user) }
    }

    @Test
    fun deleteUser_shouldDelegateToService() = runTest {
        val expected = Response.success(mapOf("message" to "ok"))
        coEvery { userService.deleteUser("u1") } returns expected

        val result = dataSource.deleteUser("u1")

        assertEquals(expected, result)
        coVerify(exactly = 1) { userService.deleteUser("u1") }
    }
}
