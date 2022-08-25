package com.example.fampayassignmentapp.di

import com.example.fampayassignmentapp.data.remote.FampayService
import com.example.fampayassignmentapp.data.repository.FampayRepoImpl
import com.example.fampayassignmentapp.domain.repository.FampayRepo
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesFamPayRepository(service: FampayService): FampayRepo = FampayRepoImpl(service)
}