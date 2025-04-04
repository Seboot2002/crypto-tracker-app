package com.sebascamayo.cryptotracker.crypto.presentation.coin_detail

import java.text.NumberFormat
import java.util.Locale

// Label del precio con formato e icono de dinero

data class ValueLabel(
    val value: Float,
    val unit: String
) {
    fun formatted(): String {
        val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
            val fractionDigits = when {
                value > 1000 -> 0
                value in 2f..999f -> 2
                else -> 3
            }
            maximumIntegerDigits = fractionDigits
            minimumIntegerDigits = 0
        }
        return "${formatter.format(value)}$unit"
    }
}
