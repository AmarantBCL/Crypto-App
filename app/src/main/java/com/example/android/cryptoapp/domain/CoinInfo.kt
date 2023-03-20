package com.example.android.cryptoapp.domain

data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String? = null,
    val price: String? = null,
    val lastUpdate: Long? = null,
    val highDay: String? = null,
    val lowDay: String? = null,
    val lastMarket: String? = null,
    val imageUrl: String? = null
)
