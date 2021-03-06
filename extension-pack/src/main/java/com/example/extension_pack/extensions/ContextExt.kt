package com.example.extension_pack.extensions

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.telephony.TelephonyManager
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor

fun Context.inflateLayout(
    @LayoutRes resource: Int,
    root: ViewGroup? = null,
    attachToRoot: Boolean = false
): View = LayoutInflater.from(this).inflate(resource, root, attachToRoot)

fun Context.getColorCompat(@ColorRes resId: Int): Int = getColor(this, resId)

fun Context.getNetworkOperator(): String? {
    return try {
        (getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).networkOperatorName
    } catch (ex: Exception) {
        null
    }
}

@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}

inline fun <reified T : AppCompatActivity> Context.startActivity(
    afterStartActivity: () -> Unit = {}
) {
    startActivity(Intent(this, T::class.java))
    afterStartActivity()
}

fun Context.copyToClipboard(text: String, label: String = "") {
    val clipboard = ContextCompat.getSystemService(this, ClipboardManager::class.java)
    val clip = ClipData.newPlainText(label, text)
    clipboard?.setPrimaryClip(clip)
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.openUrl(url: String) {
    val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(urlIntent, null)
}
