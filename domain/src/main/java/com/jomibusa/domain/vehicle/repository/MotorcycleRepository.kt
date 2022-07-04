package com.jomibusa.domain.vehicle.repository

import com.jomibusa.domain.vehicle.model.Motorcycle

interface MotorcycleRepository : VehicleRepository {

    suspend fun getAllMotorcycles(): List<Motorcycle>

}