package com.doodleblue.project.coins.repository

import android.app.Activity
import android.content.Context
import android.util.Log
import com.doodleblue.project.retrofit.api.APIClient
import com.doodleblue.project.retrofit.api.APIInterface
import com.doodleblue.project.retrofit.model.ApiListModel
import com.doodleblue.project.utils.KEYS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CoinDatas {
    object Holder {
        val value = synchronized(CoinDatas::class.java) {
            CoinDatas()
        }
    }

    companion object {
        val instance: CoinDatas by lazy { Holder.value }
    }

    init {

    }

    fun getCointList(callback: ((coinList: ApiListModel, err: String) -> Unit)) {
        var apiInterface: APIInterface? = null
        apiInterface = APIClient.getClient()!!.create(APIInterface::class.java)
        val call: Call<ApiListModel> = apiInterface.getListResources()
        call.enqueue(object : Callback<ApiListModel?> {
            override fun onFailure(call: Call<ApiListModel?>, t: Throwable) {
                Log.d("data", t.message!!)

                callback(ApiListModel(), t.message!!)

            }

            override fun onResponse(call: Call<ApiListModel?>, response: Response<ApiListModel?>) {
                if (response.isSuccessful) {
                    callback(response.body()!!, "")
                } else {
                    callback(ApiListModel(), KEYS.ERROR)
                }
                Log.d("data", response.body().toString())
            }

        })
    }
}