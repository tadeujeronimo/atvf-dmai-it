package com.tadeujeronimo.deliveryapp.data.remote

import com.tadeujeronimo.deliveryapp.data.models.UserLogin
import com.tadeujeronimo.deliveryapp.data.models.UserLoginResponse
import com.tadeujeronimo.deliveryapp.data.service.LoginService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class LoginRemoteDataSourceTest {

    private val loginService = mockk<LoginService>()
    private val dataSource = LoginRemoteDataSource(loginService)

    @Test
    fun makeLogin_shouldDelegateToService() = runTest {
        val request = UserLogin("user@email.com", "123")
        val expected = Response.success(UserLoginResponse("user@email.com", "token", false))
        coEvery { loginService.makeLogin(request) } returns expected

        val result = dataSource.makeLogin(request)

        assertEquals(expected, result)
        coVerify(exactly = 1) { loginService.makeLogin(request) }
    }
}
