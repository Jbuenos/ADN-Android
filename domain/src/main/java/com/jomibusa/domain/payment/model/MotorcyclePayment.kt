package com.jomibusa.domain.payment.model

import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.vehicle.model.Motorcycle

class MotorcyclePayment(register: Register) : Payment(register) {

    override val costByDay: Double
        get() = 4000.0

    override val costByHour: Double
        get() = 500.0

    override fun validateTotalService(): Double {
        return if (((register.vehicle) as Motorcycle).cylinderCapacity > 500) {
            super.validateTotalService() + 2000.0
        } else {
            super.validateTotalService()
        }
    }
}