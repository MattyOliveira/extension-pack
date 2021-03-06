package com.example.extension_pack.extensions

import java.util.*

fun Long.parseMonetaryValue(value: Long)= String.format("%s %.2f", "R$", value.toDouble() * 0.01)

fun Long.parseCurrency(value: Long): String  =  String.format("%s %.2f", "R$", value.toDouble() * 0.01)

fun Long.parseMonetaryWithoutDecimals(value: Long) = String.format("%s %s", "R$", value / 100).toInt()

fun Long.fromMillisToDate(): Date {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.time
}

fun Long?.isNotNullOrZero(block: (Long) -> Unit) {
    if (this != null && this != 0L) {
        block(this)
    }
}
