package com.example.newsrealtime.model

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.compose.ui.input.key.Key.Companion.Refresh

@Suppress("DEPRECATION")
class ConnectionManager {

    fun VerifyConnection(context: Context): Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

}