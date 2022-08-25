package com.example.fampayassignmentapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.fampayassignmentapp.domain.preferences.CardPreference
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {

    @Inject
    lateinit var pref: CardPreference
    override fun onCreate() {
        super.onCreate()
        pref.removeTempCardList()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}