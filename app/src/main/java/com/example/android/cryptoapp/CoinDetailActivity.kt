package com.example.android.cryptoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.android.cryptoapp.api.ApiFactory
import com.example.android.cryptoapp.viewmodel.CoinViewModel
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var textViewPrice: TextView
    private lateinit var textViewMinPrice: TextView
    private lateinit var textViewMaxPrice: TextView
    private lateinit var textViewLastMarket: TextView
    private lateinit var textViewUpdate: TextView
    private lateinit var textViewFromSymbol: TextView
    private lateinit var textViewToSymbol: TextView
    private lateinit var imageViewLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        if (fromSymbol == null) {
            finish()
            return
        }
        initViews()
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol).observe(this) {
            textViewPrice.text = it.price
            textViewMinPrice.text = it.lowDay
            textViewMaxPrice.text = it.highDay
            textViewLastMarket.text = it.lastMarket
            textViewUpdate.text = it.getFormattedTime()
            textViewFromSymbol.text = it.fromSymbol
            textViewToSymbol.text = it.toSymbol
            Picasso.get().load(it.getFullImageUrl()).into(imageViewLogo)
        }
    }

    private fun initViews() {
        textViewPrice = findViewById(R.id.tv_price)
        textViewMinPrice = findViewById(R.id.tv_day_min)
        textViewMaxPrice = findViewById(R.id.tv_day_max)
        textViewLastMarket = findViewById(R.id.tv_last_market)
        textViewUpdate = findViewById(R.id.tv_last_update)
        textViewFromSymbol = findViewById(R.id.tv_from_symbol)
        textViewToSymbol = findViewById(R.id.tv_to_symbol)
        imageViewLogo = findViewById(R.id.img_big_coin_logo)
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}