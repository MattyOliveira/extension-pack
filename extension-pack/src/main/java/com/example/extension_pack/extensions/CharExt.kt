package com.example.extension_pack.extensions

import java.text.Normalizer
import java.text.Normalizer.normalize

fun Char.removeAccents(): Char = normalize(toString(), Normalizer.Form.NFD).removeAccents().first()
