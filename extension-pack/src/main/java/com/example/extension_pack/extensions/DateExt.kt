package com.example.extension_pack.extensions

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

private const val TAG_PARSE_DATE_ERROR = "TAG_PARSE_DATE_ERROR"
private const val TIME_FORMAT = "HH:mm"
private const val DAY_GREATER_VALUE = -1
private const val ONE_MINUTE_IN_MILLIS = 60000L
private const val TO_WEEK_END = 6
private const val REWIND_THREE_WEEKS = -20
private const val NEXT_WEEK = 7
private const val LAST_WEEK = -7
private const val FROM_MONDAY_TO_NEXT_WEEK_WEDNESDAY = 9

const val YEAR_MONTH_DAY_FORMAT = "yyyy-MM-dd"
const val DATE_FORMAT_DD = "dd"
const val DATE_FORMAT_YYYY = "yyyy"

fun Date.getTimeFromDate(): String {
    val dateFormat = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
    return dateFormat.format(Date(this.time))
}

fun Date.getDateFormatted(format: String = TIME_FORMAT): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(Date(this.time))
}

fun Date.getDayAndMonthName(isFullName: Boolean = false): String {
    val cal = Calendar.getInstance()
    cal.time = this

    val month: String = cal.getDisplayName(
        Calendar.MONTH,
        if (isFullName) Calendar.LONG else Calendar.SHORT,
        Locale.getDefault()
    )

    return getDateFormatted(DATE_FORMAT_DD).plus(" ").plus(month)
}

fun Date.getDayMonthNameAndYear(isFullName: Boolean = false): String =
    getDayAndMonthName(isFullName).plus(" ").plus(getDateFormatted(DATE_FORMAT_YYYY))

fun Date.isCurrentYear(): Boolean {
    val currentCalendar = Calendar.getInstance()
    val dateCalendar = Calendar.getInstance()
    dateCalendar.time = this

    return currentCalendar.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR)
}

fun Calendar.isTimePastGivenHour(giverHour: String): Boolean {
    val currentTime = this.time
    val givenHourValues = giverHour.split(":")
    val givenHourOfDay = givenHourValues.first().toInt()
    val givenMinute = givenHourValues.last().toInt()
    val afterTime = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, givenHourOfDay)
        set(Calendar.MINUTE, givenMinute)
        set(Calendar.SECOND, 0)
    }.time
    return currentTime.after(afterTime)
}

fun Calendar.forwardToWeekEnd() = add(Calendar.DAY_OF_WEEK, TO_WEEK_END)

fun Calendar.rewindThreeWeeks() = add(Calendar.DAY_OF_WEEK, REWIND_THREE_WEEKS)

fun Calendar.nextWeek() = add(Calendar.DAY_OF_WEEK, NEXT_WEEK)

fun Date.forwardToNextWeekWednesday(): Date =
    Calendar.getInstance().apply {
        time = this@forwardToNextWeekWednesday
        set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        add(Calendar.DAY_OF_WEEK, FROM_MONDAY_TO_NEXT_WEEK_WEDNESDAY)
    }.time

fun Date.lastWeek(): Date =
    Calendar.getInstance().apply {
        time = this@lastWeek
        add(Calendar.DAY_OF_WEEK, LAST_WEEK)
    }.time


@JvmOverloads
fun isTodayGreaterThan(savedDate: String, pattern: String = YEAR_MONTH_DAY_FORMAT): Boolean {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())

    val lastDateParsed: Date? = dateFormat.parse(savedDate)
    val nowDateParsed: Date? = dateFormat.parse(Calendar.getInstance().parseToDateString(pattern))

    return lastDateParsed?.compareTo(nowDateParsed) == DAY_GREATER_VALUE
}

fun String.parseToDate(pattern: String): Date =
    SimpleDateFormat(pattern, Locale.getDefault()).parse(this)


fun String.convertToCalendar(format: String): Calendar =
    Calendar.getInstance().apply {
        time = parseToDate(format)
    }

fun String.getDayOfWeek(format: String): Int {
    return this.convertToCalendar(format).get(Calendar.DAY_OF_WEEK)
}

fun String.getDayOfMoth(format: String): String {
    return this.convertToCalendar(format).get(Calendar.DAY_OF_MONTH).toString()
}

fun String.getHour(pattern: String) = Calendar.getInstance().apply {
        time = this@getHour.parseToDate(pattern)
    }.get(Calendar.HOUR_OF_DAY).toString()

@JvmOverloads
fun Calendar.parseToDateString(pattern: String = YEAR_MONTH_DAY_FORMAT): String {
    try {
        return SimpleDateFormat(pattern, Locale.getDefault()).format(this.time)
    } catch (e: Exception) {
        Log.i(TAG_PARSE_DATE_ERROR, e.printStackTrace().toString())
        throw RuntimeException(e)
    }
}

fun Date.addDays(days: Int): Date {
    return Calendar.getInstance().apply {
        time = this@addDays
        add(Calendar.DAY_OF_YEAR, days)
    }.time
}

fun Long.toMillis(): Long {
    return this * ONE_MINUTE_IN_MILLIS
}

fun getDateArrayInBirthDayFormat(dateArray: List<String>): String {
    return if (dateArray.size == 3) {
        dateArray.reversed().joinToString(separator = "-")
    } else {
        ""
    }
}
