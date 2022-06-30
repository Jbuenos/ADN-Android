package com.jomibusa.domain.payservice.service

import com.jomibusa.domain.payservice.repository.PayServiceRepository
import com.jomibusa.domain.register.exception.VehicleNotFoundInParkingException
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.vehicle.model.Plate

class PayServiceService(private val repository: PayServiceRepository) {

    private suspend fun findRegisterToPayService(plate: Plate): Register? {
        return repository.findRegisterToPayService(plate)
    }

    suspend fun deleteRegister(register: Register) {
        if (findRegisterToPayService(register.vehicle.plate) != null) {
            repository.deleteRegisterByPlate(register.vehicle.plate)
        } else {
            throw VehicleNotFoundInParkingException()
        }
    }

}