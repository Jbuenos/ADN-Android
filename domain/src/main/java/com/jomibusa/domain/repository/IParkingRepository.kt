package com.jomibusa.domain.repository

import com.jomibusa.domain.aggregate.Parking
import com.jomibusa.domain.entity.Vehicle

interface IParkingRepository {

    suspend fun insertVehicleToParking(parking: Parking)

    suspend fun findVehicleFromParking(vehicle: Vehicle): Parking?

    suspend fun getAllVehiclesFromParking(): List<Parking>

    suspend fun getNumOfCars(): Int?

    suspend fun getNumOfMotorcycles(): Int?

    suspend fun deleteVehicleFromParking(vehicle: Vehicle): Boolean

}