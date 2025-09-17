package com.semin.watlism.data.system.datasource

import com.semin.watlism.data.datasource.system.DateTimeSystemDataSource
import kotlinx.datetime.TimeZone
import javax.inject.Inject
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class DateTimeSystemDataSourceImpl @Inject constructor(): DateTimeSystemDataSource {
    @OptIn(ExperimentalTime::class)
    override fun getNow() = Clock.System.now()

    override fun getTimeZone() = TimeZone.currentSystemDefault()
}