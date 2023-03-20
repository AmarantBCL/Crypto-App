package com.example.android.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.data.network.ApiFactory
import com.example.android.cryptoapp.databinding.ActivityCoinDetailBinding
import com.example.android.cryptoapp.utils.convertTimestampToTime
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL
        if (fromSymbol == null) {
            finish()
            return
        }
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol).observe(this) {
            with(binding) {
                tvPrice.text = it.price
                tvDayMin.text = it.lowDay
                tvDayMax.text = it.highDay
                tvLastMarket.text = it.lastMarket
                tvLastUpdate.text = it.lastUpdate
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
                Picasso.get().load(it.imageUrl).into(imgBigCoinLogo)
            }
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}