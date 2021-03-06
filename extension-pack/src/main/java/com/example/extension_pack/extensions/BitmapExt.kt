package com.example.extension_pack.extensions

import android.graphics.Bitmap
import android.graphics.Rect
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

fun Bitmap.crop(roi: Rect): Bitmap {
    return Bitmap.createBitmap(this, roi.left, roi.top, roi.width(), roi.height())
}

fun Bitmap.saveToFile(
    path: String,
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    quality: Int = 80
) {
    val file = File(path)
    if (!file.exists()) {
        file.createNewFile()
    }
    val os = BufferedOutputStream(FileOutputStream(file))
    this.compress(format, quality, os)
    os.flush()
    os.close()
}
