package com.jomibusa.adn_android.payment.util

import java.text.NumberFormat
import java.util.*

object Util {

    fun Double.parseCurrency(): String {
        val locale = Locale.getDefault()
        val numberFormat = NumberFormat.getCurrencyInstance(locale)
        return numberFormat.format(this)
    }

}