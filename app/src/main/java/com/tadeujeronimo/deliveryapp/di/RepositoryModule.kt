package com.tadeujeronimo.deliveryapp.di

import com.tadeujeronimo.deliveryapp.data.local.datasource.AddressLocalDataSource
import com.tadeujeronimo.deliveryapp.data.local.datasource.OrderLocalDataSource
import com.tadeujeronimo.deliveryapp.data.local.datasource.ProductFavoriteLocalDataSource
import com.tadeujeronimo.deliveryapp.data.remote.CartRemoteDataSource
import com.tadeujeronimo.deliveryapp.data.remote.LoginRemoteDataSource
import com.tadeujeronimo.deliveryapp.data.remote.ProductRemoteDataSource
import com.tadeujeronimo.deliveryapp.data.remote.UserRemoteDataSource
import com.tadeujeronimo.deliveryapp.data.repositories.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesLoginRepository(loginRemoteDataSource: LoginRemoteDataSource): LoginRepository =
        LoginRepository(loginRemoteDataSource)

    @Singleton
    @Provides
    fun providesProductRepository(
        productRemoteDataSource: ProductRemoteDataSource,
        productFavoriteLocalDataSource: ProductFavoriteLocalDataSource
    ): ProductRepository =
        ProductRepository(productRemoteDataSource, productFavoriteLocalDataSource)

    @Singleton
    @Provides
    fun providesUserRepository(userRemoteDataSource: UserRemoteDataSource): UserRepository =
        UserRepository(userRemoteDataSource)

    @Singleton
    @Provides
    fun providesCartRepository(cartRemoteDataSource: CartRemoteDataSource): CartRepository =
        CartRepository(cartRemoteDataSource)

    @Singleton
    @Provides
    fun provideAddressRepository(addressLocalDataSource: AddressLocalDataSource): AddressRepository =
        AddressRepository(addressLocalDataSource)

    @Singleton
    @Provides
    fun provideOrderRepository(orderLocalDataSource: OrderLocalDataSource): OrderRepository =
        OrderRepository(orderLocalDataSource)
}