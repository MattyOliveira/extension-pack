package com.example.extension_pack.extensions

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import com.example.extension_pack.R

fun NestedScrollView.onChangeElevationByScroll(view: View) {
    setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
        val elevation = when (scrollY) {
            0 -> 0f
            else -> view.context.resources.getDimensionPixelOffset(R.dimen.material_emphasis_disabled).toFloat()
        }

        ViewCompat.setElevation(view, elevation)
    })
}
