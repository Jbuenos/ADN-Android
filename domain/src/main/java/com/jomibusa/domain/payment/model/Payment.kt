package com.jomibusa.domain.payment.model

import com.jomibusa.domain.register.model.Register
import java.util.*
import java.util.concurrent.TimeUnit

abstract class Payment(val register: Register) {

    abstract val costByDay: Double
    abstract val costByHour: Double

    open fun calculateTotalService(): Double {
        val totalTimeToPay = getHoursAndDaysToPay()
        val totalServiceDay = costByDay * totalTimeToPay.second
        val totalServiceHour = costByHour * totalTimeToPay.first
        return totalServiceDay + totalServiceHour
    }

    protected fun getHoursAndDaysToPay(): Pair<Long, Long> {
        val timeToPay = Date().time - register.initDate.time

        val totalHours = TimeUnit.MILLISECONDS.toHours(timeToPay)
        val totalDays = TimeUnit.MILLISECONDS.toDays(timeToPay)

        return Pair(totalHours, totalDays)
    }

}