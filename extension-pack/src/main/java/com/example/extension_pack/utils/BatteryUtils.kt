package com.example.extension_pack.utils

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager

object BatteryUtils {
    fun getBatteryLevel(context: Context): Int {
        val level: Int
        val scale: Int
        try {
            val batteryIntent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
            level = batteryIntent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
            scale = batteryIntent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        } catch (ex: Exception) {
            return 50
        }

        return if (level == -1 || scale == -1) 50 else (level.toFloat() / scale.toFloat() * 100.0f).toInt()
    }
}