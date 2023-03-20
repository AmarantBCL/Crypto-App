package com.example.android.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.data.network.ApiFactory
import com.example.android.cryptoapp.data.network.model.CoinInfoDto
import com.example.android.cryptoapp.domain.CoinInfo
import com.example.android.cryptoapp.utils.convertTimestampToTime
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList: List<CoinInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.coin_list_item, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
                textViewSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                textViewPrice.text = price.toString()
                textViewUpdateTime.text = String.format(lastUpdateTemplate, lastUpdate)
                Picasso.get().load(imageUrl).into(imageViewLogo)
                itemView.setOnClickListener {
                    listener?.onCoinClick(this)
                }
            }
        }
    }

    override fun getItemCount() = coinInfoList.size

    inner class CoinInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewSymbols = view.findViewById<TextView>(R.id.tv_symbols)
        val textViewPrice = view.findViewById<TextView>(R.id.tv_price)
        val textViewUpdateTime = view.findViewById<TextView>(R.id.tv_update_time)
        val imageViewLogo = view.findViewById<ImageView>(R.id.img_coin_logo)
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }
}