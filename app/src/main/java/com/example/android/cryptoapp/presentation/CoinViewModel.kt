package com.example.android.cryptoapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.cryptoapp.data.network.ApiFactory
import com.example.android.cryptoapp.data.database.AppDatabase
import com.example.android.cryptoapp.data.network.model.CoinInfoDto
import com.example.android.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.example.android.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.android.cryptoapp.domain.GetCoinInfoListUseCase
import com.example.android.cryptoapp.domain.GetCoinInfoUseCase
import com.example.android.cryptoapp.domain.LoadDataUseCase
import com.google.gson.Gson
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}