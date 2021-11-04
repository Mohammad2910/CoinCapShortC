package com.example.coincapshortc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coincapshortc.ui.assets.AssetDetailsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, AssetDetailsFragment.newInstance())
                .commitNow()
        }

    }
}