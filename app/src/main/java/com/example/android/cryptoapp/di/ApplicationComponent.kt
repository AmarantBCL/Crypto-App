package com.example.android.cryptoapp.di

import android.app.Application
import com.example.android.cryptoapp.presentation.CoinDetailFragment
import com.example.android.cryptoapp.presentation.CoinPriceListActivity
import com.example.android.cryptoapp.presentation.CryptoApp
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    fun inject(application: CryptoApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}