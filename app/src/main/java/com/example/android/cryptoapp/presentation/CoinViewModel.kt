package com.example.android.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.android.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.android.cryptoapp.domain.GetCoinInfoListUseCase
import com.example.android.cryptoapp.domain.GetCoinInfoUseCase
import com.example.android.cryptoapp.domain.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}