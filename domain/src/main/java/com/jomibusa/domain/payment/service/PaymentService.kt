package com.jomibusa.domain.payment.service

import com.jomibusa.domain.register.exception.VehicleNotFoundInParkingException
import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.register.repository.CarRegisterRepository
import com.jomibusa.domain.register.repository.MotorcycleRegisterRepository
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle

class PaymentService(private val repository: RegisterRepository) {

    suspend fun deleteRegister(register: Register) {
        when (register.vehicle) {
            is Car -> {
                if ((repository as CarRegisterRepository).findCarRegisterByPlate(register.vehicle.plate) != null) {
                    repository.deleteRegisterByPlate(register)
                } else {
                    throw VehicleNotFoundInParkingException()
                }
            }

            is Motorcycle -> {
                if ((repository as MotorcycleRegisterRepository).findMotorcycleRegisterByPlate(
                        register.vehicle.plate
                    ) != null
                ) {
                    repository.deleteRegisterByPlate(register)
                } else {
                    throw VehicleNotFoundInParkingException()
                }
            }
        }
    }

}