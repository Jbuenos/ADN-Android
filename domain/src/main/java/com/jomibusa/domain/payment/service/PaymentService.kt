package com.jomibusa.domain.payment.service

import com.jomibusa.domain.register.exception.VehicleNotFoundInParkingException
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.register.repository.RegisterRepository

abstract class PaymentService(private val repository: RegisterRepository) {

    suspend fun deleteRegister(register: Register): Int {
        if (repository.findRegisterByPlate(register.vehicle.plate) != null) {
            return repository.deleteRegister(register)
        } else {
            throw VehicleNotFoundInParkingException()
        }
    }

}