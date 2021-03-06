package com.example.extension_pack.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun ViewModel.postDelay(millis: Long, block: () -> Unit) {
    viewModelScope.launch {
        delay(millis)
        block()
    }
}
