package com.doodleblue.project.retrofit.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ApiModel {
    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("rank")
    @Expose
    private var rank: String? = null

    @SerializedName("symbol")
    @Expose
    private var symbol: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("supply")
    @Expose
    private var supply: String? = null

    @SerializedName("maxSupply")
    @Expose
    private var maxSupply: Any? = null

    @SerializedName("marketCapUsd")
    @Expose
    private var marketCapUsd: String? = null

    @SerializedName("volumeUsd24Hr")
    @Expose
    private var volumeUsd24Hr: String? = null

    @SerializedName("priceUsd")
    @Expose
    private var priceUsd: String? = null

    @SerializedName("changePercent24Hr")
    @Expose
    private var changePercent24Hr: String? = null

    @SerializedName("vwap24Hr")
    @Expose
    private var vwap24Hr: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getRank(): String? {
        return rank
    }

    fun setRank(rank: String?) {
        this.rank = rank
    }

    fun getSymbol(): String? {
        return symbol
    }

    fun setSymbol(symbol: String?) {
        this.symbol = symbol
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getSupply(): String? {
        return supply
    }

    fun setSupply(supply: String?) {
        this.supply = supply
    }

    fun getMaxSupply(): Any? {
        return maxSupply
    }

    fun setMaxSupply(maxSupply: Any?) {
        this.maxSupply = maxSupply
    }

    fun getMarketCapUsd(): String? {
        return marketCapUsd
    }

    fun setMarketCapUsd(marketCapUsd: String?) {
        this.marketCapUsd = marketCapUsd
    }

    fun getVolumeUsd24Hr(): String? {
        return volumeUsd24Hr
    }

    fun setVolumeUsd24Hr(volumeUsd24Hr: String?) {
        this.volumeUsd24Hr = volumeUsd24Hr
    }

    fun getPriceUsd(): String? {
        return priceUsd
    }

    fun setPriceUsd(priceUsd: String?) {
        this.priceUsd = priceUsd
    }

    fun getChangePercent24Hr(): String? {
        return changePercent24Hr
    }

    fun setChangePercent24Hr(changePercent24Hr: String?) {
        this.changePercent24Hr = changePercent24Hr
    }

    fun getVwap24Hr(): String? {
        return vwap24Hr
    }

    fun setVwap24Hr(vwap24Hr: String?) {
        this.vwap24Hr = vwap24Hr
    }


}