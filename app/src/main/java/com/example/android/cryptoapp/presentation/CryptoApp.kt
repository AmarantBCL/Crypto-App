package com.example.android.cryptoapp.presentation

import android.app.Application
import com.example.android.cryptoapp.di.DaggerApplicationComponent

class CryptoApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}