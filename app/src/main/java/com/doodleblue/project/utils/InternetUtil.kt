package com.doodleblue.project.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

@Suppress("DEPRECATION")
class InternetUtil {
    companion object {
        val TYPE_WIFI: Byte = 1
        val TYPE_MOBILE: Byte = 2
        val TYPE_NOT_CONNECTED: Byte = 0
        fun networkDetaction(content: Context): Byte {
            val cm =
                content.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var activeNetwork: NetworkInfo? = null
            if (cm != null) {
                activeNetwork = cm.activeNetworkInfo
            }
            if (null != activeNetwork) {
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) return TYPE_WIFI
                if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) return TYPE_MOBILE
            }
            return TYPE_NOT_CONNECTED
        }
    }
}