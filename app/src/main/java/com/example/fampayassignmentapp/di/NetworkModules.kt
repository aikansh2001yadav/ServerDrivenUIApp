package com.example.fampayassignmentapp.di

import com.example.fampayassignmentapp.commons.createOkHttpClient
import com.example.fampayassignmentapp.commons.createWebService
import com.example.fampayassignmentapp.data.interceptor.HeaderInterceptor
import com.example.fampayassignmentapp.data.remote.FampayService
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

private const val BASE_URL = "https://run.mocky.io/v3/"

@dagger.Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {

    @Singleton
    @Provides
    fun providesHeaderInterceptor(): HeaderInterceptor =
        HeaderInterceptor()
}

@dagger.Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient =
        createOkHttpClient(headerInterceptor)

    @Singleton
    @Provides
    fun providesFampayService(okHttpClient: OkHttpClient): FampayService =
        createWebService(okHttpClient, BASE_URL)
}