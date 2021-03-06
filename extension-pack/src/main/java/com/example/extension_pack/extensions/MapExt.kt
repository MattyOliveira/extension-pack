package com.example.extension_pack.extensions

fun <K, V> Map<K, V>.ifNotEmpty(block: (Map<K, V>) -> Unit) {
    if (this.any()) {
        block(this)
    }
}
