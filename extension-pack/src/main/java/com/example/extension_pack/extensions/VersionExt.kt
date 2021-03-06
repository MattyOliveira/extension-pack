package com.example.extension_pack.extensions

import android.os.Build

private fun isVersionGreater(version: Int) = Build.VERSION.SDK_INT >= version

fun isVersionGreaterThanOreo1() = isVersionGreater(Build.VERSION_CODES.O_MR1)

fun isVersionGreaterThanPie() = isVersionGreater(Build.VERSION_CODES.P)

fun isVersionGreaterThanLollipop() = isVersionGreater(Build.VERSION_CODES.LOLLIPOP_MR1)

fun isVersionGreaterThanMarshmallow() = isVersionGreater(Build.VERSION_CODES.M)
