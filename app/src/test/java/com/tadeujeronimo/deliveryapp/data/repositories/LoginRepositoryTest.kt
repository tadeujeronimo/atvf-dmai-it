package com.tadeujeronimo.deliveryapp.data.repositories

import com.tadeujeronimo.deliveryapp.data.models.UserLogin
import com.tadeujeronimo.deliveryapp.data.models.UserLoginResponse
import com.tadeujeronimo.deliveryapp.data.remote.LoginRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class LoginRepositoryTest {

    private val remote = mockk<LoginRemoteDataSource>()
    private val repository = LoginRepository(remote)

    @Test
    fun makeLogin_shouldDelegateToRemoteDataSource() = runTest {
        val request = UserLogin("user@email.com", "123")
        val expected = Response.success(UserLoginResponse("user@email.com", "token", false))
        coEvery { remote.makeLogin(request) } returns expected

        val result = repository.makeLogin(request)

        assertEquals(expected, result)
        coVerify(exactly = 1) { remote.makeLogin(request) }
    }
}
