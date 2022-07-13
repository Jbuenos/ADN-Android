package com.jomibusa.domain.payment.service

import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.register.service.RegisterCarRepository
import javax.inject.Inject

class CarPaymentService @Inject constructor(@RegisterCarRepository private val repository: RegisterRepository) :
    PaymentService(repository)