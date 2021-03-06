package com.example.extension_pack.extensions

import java.io.File

fun File?.getFileSizeLabel(): String {
    val size = this?.length()?.div(1024)

    return if (size != null) {
        if (size >= 1024) {
            val sizeString = (size / 1024).toString()
            "$sizeString MB"
        } else {
            "$size KB"
        }
    } else {
        ""
    }
}
