package com.example.techchallengelalamove.utils

import java.math.BigDecimal
import java.text.DecimalFormat


object AppUtil {

    
    @JvmStatic
    fun addBothCharges(s: String, deliveryFee: String): String? {
        return  upToTwoPonit(convertStringToDouble(s) + convertStringToDouble(deliveryFee))
    }

    fun convertStringToDouble(str: String): Double {

        return if (!str.isNullOrEmpty() && str.contains("$")) str.replace(
            "$",
            ""
        ).toDouble() else 0.0


    }

    fun upToTwoPonit(str: Double): String {
        return String.format("$%.2f", str)
    }
}