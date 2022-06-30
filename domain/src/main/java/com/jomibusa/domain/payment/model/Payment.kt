package com.jomibusa.domain.payment.model

import com.jomibusa.domain.register.model.Register
import java.util.*

abstract class Payment(val register: Register) {

    abstract val costByDay: Double
    abstract val costByHour: Double

    open fun validateTotalService(): Double {
        val totalTimeToPay = getHoursAndDaysToPay()
        val totalServiceDay = costByDay * totalTimeToPay.second
        val totalServiceHour = costByHour * totalTimeToPay.first
        return totalServiceDay + totalServiceHour
    }

    protected fun getHoursAndDaysToPay(): Pair<Long, Long> {
        val timeToPay = Date().time - register.initDate.time

        val totalSeconds = timeToPay / 1000
        val totalMinutes = totalSeconds / 60
        val totalHours = totalMinutes / 60
        val totalDays = totalHours / 24

        return Pair(totalHours, totalDays)
    }

}