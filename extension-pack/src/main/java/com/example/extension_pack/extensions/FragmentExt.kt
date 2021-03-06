package com.example.extension_pack.extensions

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.view.ViewTreeObserver
import androidx.annotation.DimenRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

fun <T : Fragment> T.withArgs(vararg args: Pair<String, Any?>): T {
    return this.apply {
        arguments = bundleOf(*args)
    }
}

inline fun <reified T : Any?> Fragment.lazyArg(key: String) = lazy { arguments?.get(key) as T }

fun <T : Fragment> T.onVisibilityChangeListener(
    onShown: () -> Unit,
    onHidden: () -> Unit
): ViewTreeObserver.OnGlobalLayoutListener {

    val visibleFrame = Rect()
    var prevHeight = -1

    return ViewTreeObserver.OnGlobalLayoutListener {
        val activity = requireActivity()

        activity.window.decorView.getWindowVisibleDisplayFrame(visibleFrame)
        val height = visibleFrame.height()
        if (prevHeight < 0) {
            prevHeight = height
            return@OnGlobalLayoutListener
        }

        if (height < prevHeight) {
            onShown()
        } else if (height > prevHeight) {
            onHidden()
        }

        prevHeight = height
    }
}

fun <T : Fragment> T.setOnVisibleChangeListener(listener: ViewTreeObserver.OnGlobalLayoutListener) {
    requireActivity().window.decorView.viewTreeObserver.addOnGlobalLayoutListener(listener)
}

fun <T : Fragment> T.removeOnVisibleChangeListener(listener: ViewTreeObserver.OnGlobalLayoutListener) {
    requireActivity().window.decorView.viewTreeObserver.removeOnGlobalLayoutListener(listener)
}

fun Fragment.getDimensionPixelOffset(@DimenRes id: Int) =
    requireActivity().resources.getDimensionPixelOffset(id)

fun Fragment.setDisplayShowTitleEnabled(showTitle: Boolean) {
    (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(showTitle)
}

fun Fragment.openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    val packageManager = context?.packageManager
    packageManager?.let {
        if (intent.resolveActivity(it) != null) {
            startActivity(intent)
        }
    }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}
