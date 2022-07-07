package com.jomibusa.domain.payment.model

import com.jomibusa.domain.register.model.Register

class CarPayment(register: Register) : Payment(register) {

    override val costByDay: Double
        get() = 8000.0

    override val costByHour: Double
        get() = 1000.0

}