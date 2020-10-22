package com.doodleblue.project.coins.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.doodleblue.project.BR
import com.doodleblue.project.R
import com.doodleblue.project.coins.adapter.CoinRcAdapter
import com.doodleblue.project.coins.viewmodel.CoinViewModel
import com.doodleblue.project.databinding.ActivityCoinAddBinding
import com.doodleblue.project.retrofit.model.ApiModel
import com.doodleblue.project.utils.InternetUtil
import com.doodleblue.project.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private var mCoinViewModel: CoinViewModel? = null
    lateinit var mCoinList: ArrayList<ApiModel>
    lateinit var binding: ActivityCoinAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        mCoinViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        ).get(CoinViewModel::class.java)
        binding.setVariable(BR.laycmv, mCoinViewModel)
        swipe_container.setOnRefreshListener(this)
        mCoinList = ArrayList<ApiModel>()
        hideView()
        txt_no_data.setText(getString(R.string.loading))
        val i = InternetUtil.networkDetaction(this);
        if (i == InternetUtil.TYPE_MOBILE || i == InternetUtil.TYPE_WIFI) {
            mCoinViewModel!!.getCoinsData(this) { res, err ->
                if (err.length == 0) {
                    showView()
                    mCoinList = ArrayList<ApiModel>(res.getData()!!)
                    val coinRcAdapter = CoinRcAdapter(this, res.getData()!!)
                    binding.rcCoinadapter = coinRcAdapter
                } else {
                    txt_no_data.setText(getString(R.string.no_data_found))
                    hideView()
                }
            }
        } else {
            txt_no_data.setText(getString(R.string.no_network))
            Toast.makeText(this, getString(R.string.no_network), Toast.LENGTH_SHORT).show()
        }
        edt_coin_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {


                if (mCoinList.size > 0) {
                    filter(p0.toString())
                } else {
                    val i = InternetUtil.networkDetaction(this@MainActivity);
                    if (i == InternetUtil.TYPE_MOBILE || i == InternetUtil.TYPE_WIFI) {
                        Log.d("network", "Yes")
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            getString(R.string.no_network),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }


            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        edt_coin_search.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                filter(edt_coin_search.text.toString())
                return@OnEditorActionListener true
            }
            false
        })
    }

    fun filter(text: String) {
        if (text.length > 0) {
            val filterCoinArray: ArrayList<ApiModel> = ArrayList()

            for (eachcointDetail in mCoinList) {

                if (eachcointDetail.getName()!!.toLowerCase().contains(text.toLowerCase()) ||
                    eachcointDetail.getVolumeUsd24Hr()!!.toLowerCase()
                        .contains(text.toLowerCase()) ||
                    eachcointDetail.getChangePercent24Hr()!!.toLowerCase()
                        .contains(text.toLowerCase()) ||
                    eachcointDetail.getSymbol()!!.toLowerCase().contains(text.toLowerCase()) ||
                    eachcointDetail.getRank()!!.toLowerCase().contains(text.toLowerCase())
                ) {
                    filterCoinArray.add(eachcointDetail)
                }
            }
            if (filterCoinArray.size > 0) {
                showView()
                val coinRcAdapter = CoinRcAdapter(this, filterCoinArray)
                binding.rcCoinadapter = coinRcAdapter
                coinRcAdapter.notifyDataSetChanged()

            } else {
                hideView()
            }
        } else {
            val coinRcAdapter = CoinRcAdapter(this, mCoinList)
            binding.rcCoinadapter = coinRcAdapter
            coinRcAdapter.notifyDataSetChanged()
            showView()

        }
    }


    fun showView() {
        txt_no_data.visibility = View.GONE
        //edt_coin_search.visibility = View.VISIBLE
        txt_lab_title.visibility = View.VISIBLE
        txt_lab_price.visibility = View.VISIBLE
        txt_lab_change.visibility = View.VISIBLE
        rc_coin.visibility = View.VISIBLE
    }

    fun hideView() {
        rc_coin.visibility = View.GONE
        //edt_coin_search.visibility = View.GONE
        txt_no_data.visibility = View.VISIBLE
        txt_lab_title.visibility = View.GONE
        txt_lab_price.visibility = View.GONE
        txt_lab_change.visibility = View.GONE
    }


    override fun onRefresh() {


        val i = InternetUtil.networkDetaction(this@MainActivity);
        if (i == InternetUtil.TYPE_MOBILE || i == InternetUtil.TYPE_WIFI) {
            if (mCoinList != null) {
                if (mCoinList.size > 0) {
                    val coinRcAdapter = CoinRcAdapter(this, mCoinList)
                    binding.rcCoinadapter = coinRcAdapter
                    coinRcAdapter.notifyDataSetChanged()
                    swipe_container.isRefreshing = false
                }
            }
        } else {
            swipe_container.isRefreshing = false
            Toast.makeText(this@MainActivity, getString(R.string.no_network), Toast.LENGTH_SHORT)
                .show()
        }
    }
}