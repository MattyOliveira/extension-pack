package com.example.extension_pack.extensions

inline fun <reified T : Enum<T>> getOrDefault(value: String, defaultValue: T): T {
    return try {
        enumValueOf(value)
    } catch (e: Exception) {
        defaultValue
    }
}
