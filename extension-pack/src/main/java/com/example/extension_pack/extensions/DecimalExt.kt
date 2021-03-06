package com.example.extension_pack.extensions

import android.content.Context
import kotlin.math.roundToInt

fun Float.toPercentageNumber() = this.toDouble().toPercentageNumber()

fun Float.toPercentageText(context: Context) = this.toDouble().toPercentageText(context)

fun Double.toPercentageText(context: Context) = this.toPercentageNumber().toPercentageText(context)

fun Double.toPercentageNumber() = (this * 100).roundToInt()
