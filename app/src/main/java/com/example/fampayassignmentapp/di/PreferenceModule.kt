package com.example.fampayassignmentapp.di

import android.app.Application
import com.example.fampayassignmentapp.domain.preferences.CardPreference
import com.example.fampayassignmentapp.domain.preferences.CardPreferenceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @Singleton
    @Provides
    fun providesCardPref(app: Application): CardPreference =
        CardPreferenceImpl(app)

}
