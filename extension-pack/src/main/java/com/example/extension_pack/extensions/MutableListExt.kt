package com.example.extension_pack.extensions

fun <T> MutableList<T>.removeFrom(index: Int = 1) {
    if (isEmpty()) {
        throw NoSuchElementException("List is empty.")
    } else {
        subList(size - index, size).clear()
    }
}
