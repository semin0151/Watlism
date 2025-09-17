package com.semin.watlism.data.provider

import com.semin.watlism.data.datasource.system.DateTimeSystemDataSource
import com.semin.watlism.domain.provider.DateTimeProvider
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class DateTimeProviderImpl @Inject constructor(
    private val dateTimeSystemDataSource: DateTimeSystemDataSource
): DateTimeProvider {

    @OptIn(ExperimentalTime::class)
    override fun getFirstOfYearYearsAgo(years: Int): String {
        val timeZone = dateTimeSystemDataSource.getTimeZone()
        val localDateTime = dateTimeSystemDataSource.getNow().toLocalDateTime(timeZone)
        val targetYear = localDateTime.year.minus(years)
        val firstDay = LocalDate(
            year = targetYear,
            month = Month.JANUARY,
            day = 1
        )

        return firstDay.format("yyyy-MM-dd")
    }

    private fun LocalDate.format(pattern: String): String {
        val formatter = java.time.format.DateTimeFormatter.ofPattern(pattern)
        return java.time.LocalDate.of(year, month.number, day).format(formatter)
    }
}