package com.semin.watlism.domain.provider

import kotlin.time.ExperimentalTime

interface DateTimeProvider {
    @OptIn(ExperimentalTime::class)
    fun getFirstOfYearYearsAgo(years: Int): String
}