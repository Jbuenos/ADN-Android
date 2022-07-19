package com.jomibusa.adn_android.register.model

import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate

object CarVehicleRegister : VehicleRegister {

    override fun getNewVehicleToRegister(plate: String, cylinderCapacity: Int) = Car(Plate(plate))
}