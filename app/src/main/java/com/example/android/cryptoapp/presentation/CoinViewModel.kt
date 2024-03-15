package com.example.android.cryptoapp.presentation

import androidx.lifecycle.ViewModel
import com.example.android.cryptoapp.domain.GetCoinInfoListUseCase
import com.example.android.cryptoapp.domain.GetCoinInfoUseCase
import com.example.android.cryptoapp.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}