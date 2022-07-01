package com.jomibusa.domain.payment.model

import com.jomibusa.domain.register.model.Register

class CarPayment(register: Register) : Payment(register) {

    override val costByDay: Double
        get() = 8000.0

    override val costByHour: Double
        get() = 1000.0

    override fun calculateTotalService(): Double {
        val totalTimeToPay = getHoursAndDaysToPay()

        return if (totalTimeToPay.first.toInt() > 9) {
            val newDays = totalTimeToPay.second.toInt() + 1
            costByDay * newDays
        } else {
            super.calculateTotalService()
        }
    }

}