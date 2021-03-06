package com.example.extension_pack.extensions

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.StrikethroughSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat
import com.example.extension_pack.R

fun TextView.setTextColorById(@ColorRes colorIdRes: Int) {
    if (colorIdRes != 0) {
        setTextColor(context.getColorCompat(colorIdRes))
    }
}

fun TextView.setDrawableStart(@DrawableRes drawableRes: Int) {
    setCompoundDrawablesWithIntrinsicBounds(drawableRes, 0, 0, 0)
}

fun TextView.setFont(@FontRes fontRes: Int) {
    typeface = ResourcesCompat.getFont(this.context, fontRes)
}

fun TextView.setTextAsSelected(selected: Boolean = true, @FontRes defaultFont: Int = R.font.roboto) {
    if (selected) setFont(R.font.roboto) else setFont(defaultFont)
}

fun SpannableString.setStrikeThroughSpan(start: Int = 0, end: Int = length, flags: Int = 0) {
    setSpan(StrikethroughSpan(), start, end, flags)
}

fun SpannableString.addClickableSpan(
    callback: () -> Unit,
    spanStart: Int = 0,
    spanEnd: Int = length
): SpannableString {
    setSpan(object : ClickableSpan() {
        override fun onClick(v: View) {
            callback()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = false
        }
    }, spanStart, spanEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}
