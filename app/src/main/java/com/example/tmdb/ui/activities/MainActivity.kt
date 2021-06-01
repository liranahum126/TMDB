package com.example.tmdb.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tmdb.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    // TODO: 6/1/21 create base activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}