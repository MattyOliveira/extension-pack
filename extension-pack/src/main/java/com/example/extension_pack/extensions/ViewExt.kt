package com.example.extension_pack.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.core.view.updateLayoutParams
import androidx.core.widget.TextViewCompat

fun View.show() {
    visibility = VISIBLE
}

fun View.hide(keepInLayout: Boolean = false) {
    visibility = if (keepInLayout) INVISIBLE else GONE
}

fun View.invisible() {
    visibility = INVISIBLE
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun View.getDimensionPixelOffset(@DimenRes id: Int) =
    context.resources.getDimensionPixelOffset(id)

fun ViewGroup.inflateView(@LayoutRes layout: Int): View =
    LayoutInflater.from(context).inflate(layout, this, false)

fun View.showIf(setVisible: Boolean, keepInLayout: Boolean = false) {
    setVisible.then { show() } ?: hide(keepInLayout)
}

fun View.hideIf(setHide: Boolean, keepInLayout: Boolean = false) {
    setHide.then { hide(keepInLayout) } ?: show()
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.margin(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) {
    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = getDimensionPixelOffset(this) }
        top?.run { topMargin = getDimensionPixelOffset(this) }
        right?.run { rightMargin = getDimensionPixelOffset(this) }
        bottom?.run { bottomMargin = getDimensionPixelOffset(this) }
    }
}

fun View.size(w: Int? = null, h: Int? = null) {
    updateLayoutParams {
        w?.run { width = getDimensionPixelOffset(this) }
        h?.run { height = getDimensionPixelOffset(this) }
    }
}

inline fun View.onViewTreeObserved(crossinline function: () -> Unit) {
    val viewTreeObserverTemp = viewTreeObserver
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (viewTreeObserverTemp != null && viewTreeObserverTemp.isAlive)
                viewTreeObserverTemp.removeOnGlobalLayoutListener(this).run { function() }
            else
                viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
    })
}

inline fun View.afterMeasured(crossinline function: (measuredWidth: Int, measuredHeight: Int) -> Unit) {
    val viewTreeObserverTemp = viewTreeObserver
    viewTreeObserverTemp.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (viewTreeObserverTemp != null && viewTreeObserverTemp.isAlive && measuredWidth > 0 && measuredHeight > 0)
                viewTreeObserverTemp.removeOnGlobalLayoutListener(this).run { function(measuredWidth, measuredHeight) }
        }
    })
}

fun TextView.setTextAppearanceCompat(@StyleRes resId: Int) {
    TextViewCompat.setTextAppearance(this, resId)
}

fun View.clearClickListener() {
    setOnClickListener(null)
    isClickable = false
}

fun View.createRecursiveFadeInOutAnimation(duration: Long = 1000): ViewPropertyAnimator {
    return animateFade(duration, to = 0f).withEndAction {
        animateFade(duration, to = 1f).withEndAction {
            createRecursiveFadeInOutAnimation(duration)
        }
    }
}

fun View.animateFade(duration: Long, to: Float): ViewPropertyAnimator {
    return animate().setDuration(duration).alpha(to)
}
