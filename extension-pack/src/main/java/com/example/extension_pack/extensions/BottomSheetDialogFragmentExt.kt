package com.example.extension_pack.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.delay

fun FragmentActivity?.showDialog(
    dialog: BottomSheetDialogFragment,
    tag: String,
    fragmentManager: FragmentManager? = null
) {
    this?.let { hideKeyboard() }
    val manager = fragmentManager ?: this?.supportFragmentManager
    val fragment = manager?.findFragmentByTag(tag) as? BottomSheetDialogFragment
    if ((fragment == null || fragment.dialog?.isShowing?.not() == true) && manager != null) {
        dialog.showNow(manager, tag)
    }
}

fun Fragment?.showDialog(
    dialog: BottomSheetDialogFragment,
    tag: String
) {
    this?.activity?.showDialog(dialog, tag, childFragmentManager)
}

fun FragmentActivity?.hideDialog(tag: String) {
    val manager = this?.supportFragmentManager
    val dialog = manager?.findFragmentByTag(tag) as? BottomSheetDialogFragment
    if (dialog?.isDetached?.not() == true) dialog.dismiss()
}

fun Fragment?.hideDialog(tag: String) {
    this?.activity?.hideDialog(tag)
}

fun Fragment?.postDelay(millis: Long, block: () -> Unit) {
    this?.lifecycleScope?.launchWhenCreated {
        delay(millis)
        block()
    }
}
