package com.example.extension_pack.extensions

import android.text.Spanned
import androidx.core.text.HtmlCompat
import java.text.Normalizer

fun String?.replaceSpaces(patternToReplace: String): String {
    return this?.trim()?.replace(" ", patternToReplace) ?: emptyString()
}

fun String?.removeSpaces(): String {
    return replaceSpaces(emptyString())
}

fun String?.removeEnumeration(): String {
    return this.orEmpty().trim().replace("^[\\d.]+\\W+".toRegex(), emptyString())
}

fun String?.getEnumeration(): String {
    return "^[\\d.]+\\W+".toRegex().find(this.orEmpty().trim())?.value ?: emptyString()
}

fun emptyString(): String = ""

fun String.hasOneWord() = split(" ").size < 2

fun String.normalizeSpace(): String {
    return this.replace("\\s+".toRegex(), " ").trim()
}

fun String.getFirstInsideVowel(): Char? {
    val vowels = listOf('a', 'e', 'i', 'o', 'u')
    return (1 until length)
        .map { this[it].removeAccents() }
        .firstOrNull { it.toLowerCase() in vowels }
}

fun String.getFirstInsideConsonant(): Char? {
    val vowels = listOf('a', 'e', 'i', 'o', 'u')
    return (1 until length)
        .map { this[it].removeAccents() }
        .firstOrNull { it.toLowerCase() !in vowels }
}

fun String.removeAccents(): String {
    var string = Normalizer.normalize(this, Normalizer.Form.NFD)
    string = string.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "")
    return string
}

fun String.replaceDoubleSpacesAndSplit(): List<String> {
    return replace("\\s+".toRegex(), " ").split(" ")
}

fun String?.plusNullable(other: String): String? {
    if (this != null) return this.plus(other)
    return null
}

fun String.htmlToText(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_LIST)
}
