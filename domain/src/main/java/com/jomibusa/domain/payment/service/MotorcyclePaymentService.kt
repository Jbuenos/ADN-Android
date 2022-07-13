package com.jomibusa.domain.payment.service

import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.register.service.RegisterMotorcycleRepository
import javax.inject.Inject

class MotorcyclePaymentService @Inject constructor(@RegisterMotorcycleRepository private val repository: RegisterRepository) :
    PaymentService(repository)