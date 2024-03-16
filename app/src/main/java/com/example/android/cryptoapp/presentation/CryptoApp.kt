package com.example.android.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.android.cryptoapp.data.database.AppDatabase
import com.example.android.cryptoapp.data.mapper.CoinMapper
import com.example.android.cryptoapp.data.network.ApiFactory
import com.example.android.cryptoapp.data.workers.RefreshDataWorker
import com.example.android.cryptoapp.data.workers.RefreshDataWorkerFactory
import com.example.android.cryptoapp.di.DaggerApplicationComponent
import javax.inject.Inject

class CryptoApp : Application(), Configuration.Provider {

    @Inject
    lateinit var refreshDataWorkerFactory: RefreshDataWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(refreshDataWorkerFactory)
            .build()
    }
}