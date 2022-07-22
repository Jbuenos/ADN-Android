package com.jomibusa.adn_android.register.model

import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.model.MotorcycleRegister
import com.jomibusa.domain.register.service.CarRegisterService
import com.jomibusa.domain.register.service.MotorcycleRegisterService
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Vehicle
import java.util.*
import javax.inject.Inject

class RegisterProvider @Inject constructor(
    private val carRegisterService: CarRegisterService,
    private val motorcycleRegisterService: MotorcycleRegisterService
) {

    suspend fun insertNewRegister(vehicle: Vehicle) {
        when (vehicle) {
            is Car -> carRegisterService.insertNewRegister(CarRegister(vehicle, Date()))
            is Motorcycle -> motorcycleRegisterService.insertNewRegister(
                MotorcycleRegister(
                    vehicle,
                    Date()
                )
            )
        }
    }
}