package com.jomibusa.domain.vehicle.service

import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Vehicle
import com.jomibusa.domain.vehicle.repository.CarRepository
import com.jomibusa.domain.vehicle.repository.MotorcycleRepository
import com.jomibusa.domain.vehicle.repository.VehicleRepository

class VehicleService(
    private val vehicleRepository: VehicleRepository
) {

    suspend fun getAllCars(): List<Car> = (vehicleRepository as CarRepository).getAllCars()

    suspend fun getAllMotorcycles(): List<Motorcycle> =
        (vehicleRepository as MotorcycleRepository).getAllMotorcycles()

    suspend fun getVehicleByPlate(register: Register): Vehicle? {
        return when (register.vehicle) {
            is Car -> {
                (vehicleRepository as CarRepository).findVehicleByPlate(register)
            }
            is Motorcycle -> {
                (vehicleRepository as MotorcycleRepository).findVehicleByPlate(register)
            }
            else -> null
        }
    }

}