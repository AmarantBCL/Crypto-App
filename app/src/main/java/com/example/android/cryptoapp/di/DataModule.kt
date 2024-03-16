package com.example.android.cryptoapp.di

import android.app.Application
import com.example.android.cryptoapp.data.database.AppDatabase
import com.example.android.cryptoapp.data.database.CoinInfoDao
import com.example.android.cryptoapp.data.network.ApiFactory
import com.example.android.cryptoapp.data.network.ApiService
import com.example.android.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.android.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideCoinInfoDao(application: Application): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}