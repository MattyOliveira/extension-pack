package com.example.extension_pack.extensions

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData

inline fun <reified T : Any> Activity.extra(key: String, default: T): Lazy<T> {
    return lazy {
        val value = intent?.extras?.get(key)
        if (value is T) value else default
    }
}

inline fun <reified T : Any> extra(intent: Intent, key: String, default: T): Lazy<T> {
    return lazy {
        val value = intent.extras?.get(key)
        if (value is T) value else default
    }
}

inline fun <reified T : Any> extra(intent: Intent, key: String): Lazy<T?> {
    return lazy {
        val value = intent.extras?.get(key)
        if (value is T) value else null
    }
}

fun <T> AppCompatActivity.onBindLiveData(liveData: LiveData<T>, handler: (T) -> Unit) {
    liveData.observe(this, { handler(it) })
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}
