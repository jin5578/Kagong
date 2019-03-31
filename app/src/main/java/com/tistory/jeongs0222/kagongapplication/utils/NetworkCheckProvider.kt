package com.tistory.jeongs0222.kagongapplication.utils

import android.content.Context
import android.net.ConnectivityManager


interface NetworkCheckProvider {

    fun isNetworkConeected(): Boolean
}

class NetworkCheckProviderImpl(private val context: Context): NetworkCheckProvider {

    override fun isNetworkConeected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo

        return if(activeNetwork != null) {
            when {
                activeNetwork.type == ConnectivityManager.TYPE_WIFI -> true

                activeNetwork.type == ConnectivityManager.TYPE_MOBILE -> true

                activeNetwork.type == ConnectivityManager.TYPE_WIMAX -> true

                else -> false
            }
        } else {
            false
        }

    }
}


