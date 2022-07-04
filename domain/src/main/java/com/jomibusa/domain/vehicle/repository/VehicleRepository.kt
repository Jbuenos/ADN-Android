package com.jomibusa.domain.vehicle.repository

import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.domain.vehicle.model.Vehicle

interface VehicleRepository {

    suspend fun insertVehicle(register: Register)

    suspend fun findVehicleByPlate(register: Register): Vehicle?

    suspend fun deleteVehicle(vehicle: Vehicle): Int

}