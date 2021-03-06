package com.example.extension_pack.extensions

import android.widget.EditText

fun EditText.isNotEmpty() = text.toString().isNotEmpty()

fun EditText?.isNullOrEmpty(): Boolean = this?.let { text.toString().isEmpty() } ?: true

fun EditText.getString() = text.toString()
