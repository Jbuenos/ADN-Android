package com.jomibusa.adn_android.register.model

import com.jomibusa.domain.vehicle.model.Vehicle

interface VehicleRegister {

    fun getNewVehicleToRegister(plate: String, cylinderCapacity: Int = 0): Vehicle

}