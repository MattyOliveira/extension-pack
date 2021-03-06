package com.example.extension_pack.extensions

fun <T> List<T>.hasOneItem(): Boolean = size == 1

fun <T> List<T>.hasMoreThanOneItem(): Boolean = size > 1

fun <T> List<T>.ifNotEmpty(block: (List<T>) -> Unit) {
    if (this.any()) {
        block(this)
    }
}

fun Int.isInvalidIndex(): Boolean = this == -1
