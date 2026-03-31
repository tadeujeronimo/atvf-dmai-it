package com.tadeujeronimo.deliveryapp.di

import com.tadeujeronimo.deliveryapp.data.local.dao.AddressDao
import com.tadeujeronimo.deliveryapp.data.local.dao.OrderDao
import com.tadeujeronimo.deliveryapp.data.local.dao.ProductFavoriteDao
import com.tadeujeronimo.deliveryapp.data.local.datasource.AddressLocalDataSource
import com.tadeujeronimo.deliveryapp.data.local.datasource.OrderLocalDataSource
import com.tadeujeronimo.deliveryapp.data.local.datasource.ProductFavoriteLocalDataSource
import com.tadeujeronimo.deliveryapp.data.remote.LoginRemoteDataSource
import com.tadeujeronimo.deliveryapp.data.service.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideProductFavoriteLocalDataSource(productFavoriteDao: ProductFavoriteDao): ProductFavoriteLocalDataSource =
        ProductFavoriteLocalDataSource(productFavoriteDao)

    @Singleton
    @Provides
    fun provideAddressLocalDataSource(addressDao: AddressDao): AddressLocalDataSource =
        AddressLocalDataSource(addressDao)

    @Singleton
    @Provides
    fun provideOrderLocalDataSource(orderDao: OrderDao): OrderLocalDataSource =
        OrderLocalDataSource(orderDao)
}


