package com.jomibusa.domain.vehicle.service

import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.domain.vehicle.model.Vehicle
import com.jomibusa.domain.vehicle.repository.VehicleRepository

class VehicleService(private val repository: VehicleRepository) {

    suspend fun getAllVehicles(): List<Vehicle>? {
        return repository.getAllVehicles()
    }

    suspend fun getVehicleByPlate(plate: Plate): Vehicle? {
        return repository.findVehicleByPlate(plate)
    }

}