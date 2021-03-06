package com.example.extension_pack.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder


abstract class BaseSharedPreferences(context: Context, key: String) {
    var sharedPreferences: SharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)

    inline fun <reified T> put(obj: T, key: String) {
            val jsonString = GsonBuilder().create().toJson(obj)
            sharedPreferences.edit().putString(key, jsonString).apply()
    }

    inline fun <reified T> get(key: String): T? {
        val value = sharedPreferences.getString(key, null)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }
}