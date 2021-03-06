package com.example.extension_pack.extensions

import android.content.Context
import com.example.extension_pack.R
import java.util.Locale

fun Int.isFirst(): Boolean {
    return this == 0
}

fun Int.getRotationCompensation(): Int {
    return (360 - this) % 360
}

fun Int.toPercentageText(context: Context) =
    context.getString(R.string.percet_format, this)

fun Int.toMonthText(context: Context, decapitalize: Boolean = false): String? {
    return if (this in 1..12) {
        val arrayMonthIndex = this - 1
        return context.resources.getStringArray(R.array.month)[arrayMonthIndex]
            .let { if (decapitalize) it.decapitalize(Locale.ROOT) else it }
    } else null
}

fun Int?.isNotNullOrZero(block: (Int) -> Unit) {
    if (this != null && this != 0) block(this)
}

