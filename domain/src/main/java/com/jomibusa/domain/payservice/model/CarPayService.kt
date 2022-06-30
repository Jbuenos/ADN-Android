package com.jomibusa.domain.payservice.model

import com.jomibusa.domain.register.model.Register

class CarPayService(register: Register) : PayService(register) {

    override val costByDay: Double
        get() = 8000.0

    override val costByHour: Double
        get() = 1000.0

    override fun validateTotalService(): Double {
        val totalTimeToPay = getHoursAndDaysToPay()

        return if (totalTimeToPay.first.toInt() > 9) {
            val newDays = totalTimeToPay.second.toInt() + 1
            costByDay * newDays
        } else {
            super.validateTotalService()
        }
    }

}