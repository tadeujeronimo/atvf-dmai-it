package com.tadeujeronimo.deliveryapp.di

import com.tadeujeronimo.deliveryapp.data.service.CartService
import com.tadeujeronimo.deliveryapp.data.service.LoginService
import com.tadeujeronimo.deliveryapp.data.service.ProductService
import com.tadeujeronimo.deliveryapp.data.service.UserService
import com.tadeujeronimo.deliveryapp.data.util.AuthInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit.Builder =
        Retrofit.Builder().baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)

    @Singleton
    @Provides
    fun provideLoginService(retrofit: Retrofit.Builder): LoginService =
        retrofit.build().create(LoginService::class.java)

    @Singleton
    @Provides
    fun provideProductService(retrofit: Retrofit.Builder): ProductService =
        retrofit.build().create(ProductService::class.java)

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit.Builder): UserService =
        retrofit.build().create(UserService::class.java)

    @Singleton
    @Provides
    fun provideCartService(retrofit: Builder): CartService =
        retrofit.build().create(CartService::class.java)

}