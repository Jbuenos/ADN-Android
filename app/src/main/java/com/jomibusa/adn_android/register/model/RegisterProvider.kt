package com.jomibusa.adn_android.register.model

import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.model.MotorcycleRegister
import com.jomibusa.domain.register.service.RegisterService
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Vehicle
import java.util.*
import javax.inject.Inject

class RegisterProvider @Inject constructor(private val registerService: RegisterService) {

    suspend fun insertNewRegister(vehicle: Vehicle) {
        val register = when (vehicle) {
            is Car -> CarRegister(vehicle, Date())
            else -> MotorcycleRegister(vehicle, Date())
        }
        registerService.insertNewRegister(register)
    }
}