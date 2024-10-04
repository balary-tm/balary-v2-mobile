package tm.com.balary.core.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

fun getFormattedDate(daysOffset: Int = 0): String {
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val targetDate = today.plus(DatePeriod(days = daysOffset))
    return targetDate.toString("dd.MM.yyyy")
}

// Extension function to format date as "dd.MM.yyyy"
fun LocalDate.toString(pattern: String): String {
    val day = this.dayOfMonth.toString().padStart(2, '0')
    val month = this.monthNumber.toString().padStart(2, '0')
    val year = this.year.toString()
    return "$day.$month.$year"
}