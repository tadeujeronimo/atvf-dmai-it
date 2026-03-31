package com.tadeujeronimo.deliveryapp.di

import com.tadeujeronimo.deliveryapp.data.remote.CartRemoteDataSource
import com.tadeujeronimo.deliveryapp.data.remote.LoginRemoteDataSource
import com.tadeujeronimo.deliveryapp.data.remote.ProductRemoteDataSource
import com.tadeujeronimo.deliveryapp.data.remote.UserRemoteDataSource
import com.tadeujeronimo.deliveryapp.data.service.CartService
import com.tadeujeronimo.deliveryapp.data.service.LoginService
import com.tadeujeronimo.deliveryapp.data.service.ProductService
import com.tadeujeronimo.deliveryapp.data.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Singleton
    @Provides
    fun providesLoginRemoteDataSource(loginService: LoginService): LoginRemoteDataSource =
        LoginRemoteDataSource(loginService)

    @Singleton
    @Provides
    fun provideProductRemoteDataSource(productService: ProductService): ProductRemoteDataSource =
        ProductRemoteDataSource(productService)

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(userService: UserService): UserRemoteDataSource =
        UserRemoteDataSource(userService)

    @Singleton
    @Provides
    fun provideCartRemoteDataSource(cartService: CartService): CartRemoteDataSource =
        CartRemoteDataSource(cartService)
}