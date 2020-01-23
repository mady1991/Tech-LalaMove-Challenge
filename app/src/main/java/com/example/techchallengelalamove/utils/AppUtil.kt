package com.example.techchallengelalamove.utils

import java.math.BigDecimal
import java.text.DecimalFormat


object AppUtil {

    const val DATABASE_PAGE_SIZE = 20

    @JvmStatic
    fun addBothCharges(surcharge: String, deliveryFee: String): String? {
        return String.format(
            "$%.2f",
            convertStringToBigDecimal(surcharge) + convertStringToBigDecimal(deliveryFee)
        )
    }

    fun convertStringToBigDecimal(str: String?): BigDecimal {
        return if (str?.contains("$") == true)
            str.replace("$", "").toBigDecimal() else 0.0.toBigDecimal()

    }
}