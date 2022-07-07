package com.jomibusa.domain.register.service

import com.jomibusa.domain.register.exception.CapacityParkingExceededException
import com.jomibusa.domain.register.exception.ExistSameVehicleException
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.vehicle.model.Plate

class RegisterService(private val repository: RegisterRepository) {

    private suspend fun validateMaxSpaces(register: Register): Boolean {
        val numVehicles = repository.getAllRegisters().size
        if (numVehicles >= register.capacityParking) throw CapacityParkingExceededException() else return true
    }

    private suspend fun findPreviousVehicleRegister(plate: Plate): Register? =
        repository.findRegisterByPlate(plate)

    suspend fun insertNewRegister(register: Register) {
        if (validateMaxSpaces(register)) {
            if (findPreviousVehicleRegister(register.vehicle.plate) == null) {
                repository.insertRegister(register)
            } else {
                throw ExistSameVehicleException()
            }
        }
    }

}