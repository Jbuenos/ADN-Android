package com.jomibusa.adn_android.register.model

import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate

object MotorcycleVehicleRegister : VehicleRegister {

    override fun getNewVehicleToRegister(plate: String, cylinderCapacity: Int) =
        Motorcycle(cylinderCapacity, Plate(plate))
}