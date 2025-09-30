package com.semin.watlism.feature.core

import android.util.Log

object Logs {
    private const val TAG = "WATLISM_LOGS"

    fun e(message: String) {
        Log.e(TAG, "${Throwable().stackTrace[1]} -> $message")
    }
}