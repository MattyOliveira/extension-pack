package com.example.extension_pack.utils

import java.text.DateFormat
import java.text.FieldPosition
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
const val DD_MM_YYYY = "dd/MM/yyyy"
const val YYYY_MM_DD = "yyyy-MM-dd"
const val MM_YYYY = "MM/yyyy"
const val TIME_FORMAT = "HH:mm"
const val MINUTES_SECONDS_FORMAT = "mm:ss"

object DateUtils {

    fun getTimeFromDate(date: Long): String? = getTimeFromDate(TIME_FORMAT, date)

    fun getMinutesSecondsFromLong(millis: Long) = getTimeFromDate(MINUTES_SECONDS_FORMAT, millis)

    fun getCurrentDate(): String? = SimpleDateFormat(DD_MM_YYYY, Locale.getDefault()).format(Date())

    fun getTimeFromDate(pattern: String?, date: Long): String? {
        val data: DateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return data.format(Date(date))
    }

    fun plusSecondOnDate(date: Date, seconds: Int): Date? =
        plusTime(date, Calendar.SECOND, seconds).time

    fun plusMinutesOnDate(date: Date, minutes: Int): Date? =
        plusTime(date, Calendar.MINUTE, minutes).time

    fun minusMinutesOnDate(date: Date, minutes: Int): Date? =
        plusTime(date, Calendar.MINUTE, -minutes).time

    private fun plusTime(date: Date, field: Int, minutes: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(field, minutes)
        return calendar
    }

    fun convertStringFormatToDate(date: String): Date? = getFormat().parse(date)

    fun getTimeDifferenceInHour(firstDate: Date, lastDate: Date): Long =
        TimeUnit.MILLISECONDS.toHours(lastDate.time - firstDate.time)

    private fun getFormat(): SimpleDateFormat {
        return object : SimpleDateFormat(DATE_FORMAT, Locale.getDefault()) {
            override fun format(
                date: Date,
                toAppendTo: StringBuffer,
                pos: FieldPosition
            ): StringBuffer {
                val toFix = super.format(date, toAppendTo, pos)
                return toFix.insert(toFix.length - 2, ':')
            }
        }
    }
}