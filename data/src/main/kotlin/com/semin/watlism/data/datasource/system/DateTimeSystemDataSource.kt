package com.semin.watlism.data.datasource.system

import kotlinx.datetime.TimeZone
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

interface DateTimeSystemDataSource {
    @OptIn(ExperimentalTime::class)
    fun getNow(): Instant

    fun getTimeZone(): TimeZone
}