package com.example.extension_pack.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {

    fun isConnected(context: Context): Boolean{
        val networkInfo = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        return (networkInfo != null && networkInfo.isConnected)
    }
}