package com.jomibusa.domain.register.service

import com.jomibusa.domain.register.exception.CapacityParkingExceededException
import com.jomibusa.domain.register.exception.ExistSameVehicleException
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.register.repository.CarRegisterRepository
import com.jomibusa.domain.register.repository.MotorcycleRegisterRepository
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate

class RegisterService(private val repository: RegisterRepository) {

    private suspend fun validateMaxSpaces(register: Register): Boolean {
        return when (register.vehicle) {
            is Car -> {
                val numOfCars = (repository as CarRegisterRepository).getAllCarsRegister().size
                if (numOfCars > register.capacityParking) throw CapacityParkingExceededException() else return true
            }

            is Motorcycle -> {
                val numOfMotorcycles =
                    (repository as MotorcycleRegisterRepository).getAllMotorcyclesRegister().size
                if (numOfMotorcycles > register.capacityParking) throw CapacityParkingExceededException() else return true
            }
            else -> false
        }

    }

    private suspend fun findPreviousVehicleRegister(register: Register): Register? {
        return when (register.vehicle) {
            is Car -> (repository as CarRegisterRepository).findCarRegisterByPlate(register.vehicle.numPlate)
            is Motorcycle -> (repository as MotorcycleRegisterRepository).findMotorcycleRegisterByPlate(
                register.vehicle.numPlate
            )
            else -> null
        }
    }

    suspend fun insertNewRegister(register: Register) {
        if (validateMaxSpaces(register)) {
            if (findPreviousVehicleRegister(register) == null) {
                repository.insertRegister(register)
            } else {
                throw ExistSameVehicleException()
            }
        }
    }

}