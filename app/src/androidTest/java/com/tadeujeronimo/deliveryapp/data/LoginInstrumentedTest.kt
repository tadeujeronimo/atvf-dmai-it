package com.tadeujeronimo.deliveryapp.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tadeujeronimo.deliveryapp.data.models.UserLogin
import com.tadeujeronimo.deliveryapp.data.models.UserLoginResponse
import com.tadeujeronimo.deliveryapp.data.remote.LoginRemoteDataSource
import com.tadeujeronimo.deliveryapp.data.repositories.LoginRepository
import com.tadeujeronimo.deliveryapp.data.service.LoginService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class LoginInstrumentedTest {

    @Test
    fun loginRemoteDataSource_shouldReturnServiceResponse() = runBlocking {
        val request = UserLogin("user@email.com", "123")
        val expected = UserLoginResponse("user@email.com", "token-123", false)
        val fakeService = FakeLoginService(expected)

        val dataSource = LoginRemoteDataSource(fakeService)
        val result = dataSource.makeLogin(request)

        assertTrue(result.isSuccessful)
        assertEquals(expected, result.body())
        assertEquals(request, fakeService.lastRequest)
    }

    @Test
    fun loginRepository_shouldReturnRemoteResponse() = runBlocking {
        val request = UserLogin("user@email.com", "123")
        val expected = UserLoginResponse("user@email.com", "token-456", true)
        val fakeService = FakeLoginService(expected)

        val remoteDataSource = LoginRemoteDataSource(fakeService)
        val repository = LoginRepository(remoteDataSource)
        val result = repository.makeLogin(request)

        assertTrue(result.isSuccessful)
        assertEquals(expected, result.body())
        assertEquals(request, fakeService.lastRequest)
    }

    private class FakeLoginService(
        private val responseBody: UserLoginResponse
    ) : LoginService {
        var lastRequest: UserLogin? = null

        override suspend fun makeLogin(userLogin: UserLogin): Response<UserLoginResponse> {
            lastRequest = userLogin
            return Response.success(responseBody)
        }
    }
}
