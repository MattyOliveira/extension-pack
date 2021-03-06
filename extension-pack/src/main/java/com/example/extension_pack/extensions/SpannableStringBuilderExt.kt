package com.example.extension_pack.extensions

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.View
import androidx.core.text.HtmlCompat

object SpannableStringBuilderExt {
    fun makeLinkClickable(
        context: Context?,
        html: String,
        clickFunc: (url: String, item: String) -> Unit,
        item: String? = null
    ): SpannableStringBuilder {

        val sequence: CharSequence = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)

        return SpannableStringBuilder(sequence).apply {
            getSpans(0, sequence.length, URLSpan::class.java).forEach {
                val clickable = object : ClickableSpan() {
                    override fun onClick(view: View) {
                        clickFunc(it.url, item.orEmpty())
                        context?.openUrl(it?.url.orEmpty())
                    }
                }

                setSpan(clickable, getSpanStart(it), getSpanEnd(it), getSpanFlags(it))
                removeSpan(it)
            }
        }
    }
}
