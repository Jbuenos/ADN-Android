package com.jomibusa.domain.vehicle.repository

import com.jomibusa.domain.vehicle.model.Car

interface CarRepository : VehicleRepository {

    suspend fun getAllCars(): List<Car>

}