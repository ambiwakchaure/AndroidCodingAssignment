package android.assignment.telstra.utils

import android.assignment.telstra.MyApplication
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class T
{
    companion object
    {
        //check internet connection
        fun isNetworkAvailable(): Boolean
        {
            val connectivityManager = MyApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else false
        }
    }
}