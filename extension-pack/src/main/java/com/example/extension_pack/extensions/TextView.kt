package com.example.extension_pack.extensions

import android.widget.TextView
import androidx.core.text.HtmlCompat

fun TextView.setHtmlText(text: String) {
    setText(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY))
}
