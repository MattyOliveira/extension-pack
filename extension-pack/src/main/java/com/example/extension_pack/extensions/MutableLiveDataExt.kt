package com.example.extension_pack.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this
