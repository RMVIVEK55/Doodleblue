package com.doodleblue.project.coins.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.doodleblue.project.R
import com.doodleblue.project.databinding.AdapterCoinAddBinding
import com.doodleblue.project.retrofit.model.ApiModel
import com.doodleblue.project.utils.Convert
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.Format
import java.text.NumberFormat
import java.util.*

class CoinRcAdapter(context: Context, coinList: List<ApiModel?>) :
    RecyclerView.Adapter<CoinRcAdapter.ViewHolder>() {
    var context: Context
    var coinList: List<ApiModel?>

    init {
        this.coinList = coinList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinRcAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val coinBinding: AdapterCoinAddBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_view_coins, parent, false)
        return ViewHolder(coinBinding, context)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: CoinRcAdapter.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val model = coinList[position]
            holder.bind(model!!, position)
        }
    }

    class ViewHolder(val coinBinding: AdapterCoinAddBinding, var context: Context? = null) :
        RecyclerView.ViewHolder(coinBinding.root) {
        fun bind(model: ApiModel, position: Int) {

            this.coinBinding.coinadapter = model
            coinBinding.executePendingBindings()
            coinBinding.txtPrice.setText(Convert.StrToMoney(model.getPriceUsd()!!))
            coinBinding.txtChange.setText(
                String.format(
                    Locale.UK,
                    "%.2f",
                    model.getChangePercent24Hr()!!.toFloat()
                ) + "%"
            )

            coinBinding.txtNum.setText(position.inc().toString())

        }

    }
}