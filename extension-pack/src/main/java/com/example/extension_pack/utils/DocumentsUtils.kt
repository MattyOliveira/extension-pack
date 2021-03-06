package com.example.extension_pack.utils

import android.content.Context
import java.util.*

object DocumentsUtils {
    fun isCPFValid(context: Context, CPF: String): Boolean {
        val document = CPF.replace(".", "").replace("-", "")
        return !isASequence(context, document) && calcVerifyingDigit(
            document,
            10
        ) && calcVerifyingDigit(document, 11)
    }

    private fun isASequence(context: Context, document: String): Boolean {
        return document.matches(Regex("^(\\\\d)\\\\1{10}"))
    }

    private fun calcVerifyingDigit(CPF: String, lenght: Int): Boolean {
        return try {
            isLastDigitValid(CPF, lenght)
        } catch (e: InputMismatchException) {
            false
        }
    }

    private fun isLastDigitValid(CPF: String, digitsLength: Int): Boolean {
        val sum: Int = getSum(CPF, digitsLength)
        return findCorrespondingDigit(sum) == CPF[digitsLength - 1]
    }

    private fun getSum(CPF: String, digitsLength: Int): Int {
        var sum = 0
        var weight = digitsLength
        for (i in 0 until digitsLength - 1) {
            val number = CPF[i].toInt() - 48
            sum += number * weight
            weight -= 1
        }
        return sum
    }

    private fun findCorrespondingDigit(sum: Int): Char {
        val digit: Char
        val result = 11 - sum % 11
        digit = if (result == 10 || result == 11) {
            '0'
        } else {
            (result + 48).toChar()
        }
        return digit
    }
}