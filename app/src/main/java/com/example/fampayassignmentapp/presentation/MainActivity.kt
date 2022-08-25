package com.example.fampayassignmentapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fampayassignmentapp.R
import com.example.fampayassignmentapp.presentation.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()
    }
}