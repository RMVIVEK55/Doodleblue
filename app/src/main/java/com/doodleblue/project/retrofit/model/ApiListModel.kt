package com.doodleblue.project.retrofit.model

import com.doodleblue.project.retrofit.model.ApiModel
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ApiListModel {
    @SerializedName("data")
    @Expose
    private var data: List<ApiModel?>? = null

//    @SerializedName("timestamp")
//    @Expose
//    private var timestamp: Int? = null

    fun getData(): List<ApiModel?>? {
        return data
    }

    fun setData(data: List<ApiModel?>?) {
        this.data = data
    }

//    fun getTimestamp(): Int? {
//        return timestamp
//    }
//
//    fun setTimestamp(timestamp: Int?) {
//        this.timestamp = timestamp
    //}
}