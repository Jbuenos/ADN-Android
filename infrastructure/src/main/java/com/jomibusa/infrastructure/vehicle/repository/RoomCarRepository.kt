package com.jomibusa.infrastructure.vehicle.repository

import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.domain.vehicle.model.Vehicle
import com.jomibusa.domain.vehicle.repository.VehicleRepository
import com.jomibusa.infrastructure.shared.database.ParkingDatabase
import com.jomibusa.infrastructure.vehicle.anticorruption.VehicleTranslatorInfraToDomain

class RoomCarRepository(private val parkingDatabase: ParkingDatabase) : VehicleRepository {

    override suspend fun getAllVehicles(): List<Vehicle> {
        val allCarsFromParking = parkingDatabase.carDAO.getAllCarsFromParking()
        var listDomain: List<Car> = listOf()
        allCarsFromParking?.let {
            listDomain = VehicleTranslatorInfraToDomain.parseCarEntityToDomain(it)
        }
        return listDomain
    }

    override suspend fun findVehicleByPlate(plate: Plate): Vehicle? {
        val carEntity = parkingDatabase.carDAO.findCarByPlate(plate.numPlate)
        return if (carEntity != null) VehicleTranslatorInfraToDomain.parseCarEntityToDomain(
            carEntity
        ) else null
    }
}