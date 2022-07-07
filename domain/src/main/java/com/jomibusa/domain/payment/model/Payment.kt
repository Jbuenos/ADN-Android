package com.jomibusa.domain.payment.model

import com.jomibusa.domain.register.model.Register
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.abs

abstract class Payment(val register: Register) {

    abstract val costByDay: Double
    abstract val costByHour: Double

    open fun calculateTotalService(): Double {
        val totalTimeToPay = getHoursAndDaysToPay()

        return if (totalTimeToPay.first >= 9) {
            val daysToPayment = totalTimeToPay.second + 1
            costByDay * daysToPayment
        } else {
            val totalServiceHour = costByHour * totalTimeToPay.first
            val totalServiceDay = costByDay * totalTimeToPay.second
            totalServiceDay + totalServiceHour
        }
    }

    private fun getHoursAndDaysToPay(): Pair<Int, Int> {
        val newTime = dateToLocalDateTimeWithFormat(register.initDate)
        val timeToPay = Duration.between(LocalDateTime.now(), newTime)
        val totalHours = abs(timeToPay.toHours().toInt())
        val totalDays = abs(timeToPay.toDays().toInt())

        return Pair(totalHours, totalDays)
    }

    private fun dateToLocalDateTimeWithFormat(dateToConvert: Date): LocalDateTime {
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        val formatted = dateFormatter.format(dateToConvert)
        return LocalDateTime.parse(formatted, formatter)
    }

}