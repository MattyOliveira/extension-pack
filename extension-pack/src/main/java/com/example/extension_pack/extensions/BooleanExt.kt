package com.example.extension_pack.extensions

import android.view.View

inline fun <T> Boolean.then(block: () -> T): T? {
    if (this) return block()
    return null
}

fun Boolean.getVisibleOrGoneVisibility(): Int = if (this) View.VISIBLE else View.GONE

fun Boolean.toInt() = if (this) 1 else 0
