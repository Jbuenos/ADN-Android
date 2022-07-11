package com.jomibusa.infrastructure.vehicle.repository

import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.domain.vehicle.model.Vehicle
import com.jomibusa.domain.vehicle.repository.VehicleRepository
import com.jomibusa.infrastructure.shared.database.ParkingDatabase
import com.jomibusa.infrastructure.vehicle.anticorruption.VehicleTranslatorInfraToDomain

class RoomMotorcycleRepository(private val parkingDatabase: ParkingDatabase) : VehicleRepository {

    override suspend fun getAllVehicles(): List<Vehicle> {
        val allMotorcyclesFromParking = parkingDatabase.motorcycleDAO().getAllMotorcyclesFromParking()
        var listDomain: List<Motorcycle> = listOf()
        allMotorcyclesFromParking?.let {
            listDomain = VehicleTranslatorInfraToDomain.parseMotorcycleEntityToDomain(it)
        }
        return listDomain
    }

    override suspend fun findVehicleByPlate(plate: Plate): Vehicle? {
        val motorcycleEntity =
            parkingDatabase.motorcycleDAO().findMotorcycleByPlate(plate.numPlate)
        return if (motorcycleEntity != null) VehicleTranslatorInfraToDomain.parseMotorcycleEntityToDomain(
            motorcycleEntity
        ) else null
    }
}