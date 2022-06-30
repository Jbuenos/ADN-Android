package com.jomibusa.domain.payment.model

import com.jomibusa.domain.register.model.Register

abstract class Payment(register: Register) {

    abstract val costByDay: Double
    abstract val costByHour: Double

}