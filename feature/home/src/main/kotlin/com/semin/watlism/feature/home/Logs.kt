package com.semin.watlism.feature.home

import android.util.Log

object Logs {
    private const val TAG = "WATLISM_LOGS"

    fun e(message: String) {
        Log.e(TAG, message)
    }
}