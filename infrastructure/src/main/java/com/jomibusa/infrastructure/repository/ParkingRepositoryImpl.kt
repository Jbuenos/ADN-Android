package com.jomibusa.infrastructure.repository

import com.jomibusa.domain.aggregate.Parking
import com.jomibusa.domain.entity.Vehicle
import com.jomibusa.domain.repository.IParkingRepository
import com.jomibusa.infrastructure.anticorruption.ParkingTranslatorDomainToInfra
import com.jomibusa.infrastructure.anticorruption.ParkingTranslatorInfraToDomain
import com.jomibusa.infrastructure.database.ParkingDatabase

class ParkingRepositoryImpl(
    private val parkingDatabase: ParkingDatabase,
    private val parkingTranslatorDomainToInfra: ParkingTranslatorDomainToInfra,
    private val parkingTranslatorInfraToDomain: ParkingTranslatorInfraToDomain
) : IParkingRepository {

    override suspend fun insertVehicleToParking(parking: Parking): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun findVehicleFromParking(vehicle: Vehicle): Parking? {
        TODO("Not yet implemented")
    }

    override suspend fun getAllVehiclesFromParking(): List<Parking> {
        TODO("Not yet implemented")
    }

    override suspend fun getNumOfCars(): Int? {
        return parkingDatabase.carDAO.getAllCars()?.size
    }

    override suspend fun getNumOfMotorcycles(): Int? {
        return parkingDatabase.motorcycleDAO.getAllMotorcycle()?.size
    }

    override suspend fun deleteVehicleFromParking(vehicle: Vehicle): Boolean {
        return parkingDatabase.parkingDAO.deleteVehicleParking(vehicle.plate.numPlate) != -1
    }
}