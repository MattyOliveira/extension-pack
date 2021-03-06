package com.example.extension_pack.extensions

import android.app.Service
import android.content.Context
import android.content.Intent

const val STOP_FOREGROUND_ACTION = "STOP_FOREGROUND_ACTION"

fun Context.startService(clazz: Class<out Service>) {
    startService(Intent(this, clazz))
}

fun Context.stopService(clazz: Class<out Service>) {
    stopService(Intent(this, clazz))
}

fun Context.stopServiceWithActionStop(clazz: Class<out Service>) {
    val intent = Intent(this, clazz)
    intent.action = STOP_FOREGROUND_ACTION
    stopService(intent)
}
