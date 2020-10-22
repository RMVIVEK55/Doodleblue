package com.doodleblue.project.retrofit.api

import com.doodleblue.project.retrofit.model.ApiListModel
import com.doodleblue.project.utils.KEYS
import retrofit2.Call

import retrofit2.http.GET




interface APIInterface {
    @GET(KEYS.BASE_URL_ASSETS)
    fun getListResources(): Call<ApiListModel>
}