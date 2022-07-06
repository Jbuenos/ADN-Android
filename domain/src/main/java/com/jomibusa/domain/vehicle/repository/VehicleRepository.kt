package com.jomibusa.domain.vehicle.repository

import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.domain.vehicle.model.Vehicle

interface VehicleRepository {

    suspend fun getAllVehicles(): List<Vehicle>

    suspend fun findVehicleByPlate(plate: Plate): Vehicle?

}