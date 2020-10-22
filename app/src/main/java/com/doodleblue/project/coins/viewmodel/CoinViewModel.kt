package com.doodleblue.project.coins.viewmodel

import android.content.Context
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doodleblue.project.coins.repository.CoinDatas
import com.doodleblue.project.retrofit.model.ApiListModel


class CoinViewModel(val context: Context) : ViewModel(){


    //fetch data from API
    fun getCoinsData(context: Context, callback: ((coinList: ApiListModel, err: String) -> Unit)) {

        CoinDatas.instance.getCointList({ res, err ->

            callback(res, err)
        })

    }


}