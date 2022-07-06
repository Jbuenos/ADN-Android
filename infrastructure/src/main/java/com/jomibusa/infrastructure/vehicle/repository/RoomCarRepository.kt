package com.jomibusa.infrastructure.vehicle.repository

import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.domain.vehicle.model.Vehicle
import com.jomibusa.domain.vehicle.repository.VehicleRepository
import com.jomibusa.infrastructure.shared.database.ParkingDatabase
import com.jomibusa.infrastructure.vehicle.anticorruption.VehicleTranslatorInfraToDomain

class RoomCarRepository(private val parkingDatabase: ParkingDatabase) : VehicleRepository {

    /*override suspend fun insertVehicle(register: Register) {

        when (register.vehicle) {
            is Car -> {
                val carEntity =
                    VehicleTranslatorDomainToInfra.parseCarDomainToEntity(register.vehicle as Car)
                parkingDatabase.carDAO.insertCar(carEntity)
            }
            is Motorcycle -> {
                val motorcycleEntity =
                    VehicleTranslatorDomainToInfra.parseMotorcycleDomainToEntity(register.vehicle as Motorcycle)
                parkingDatabase.motorcycleDAO.insertMotorcycle(motorcycleEntity)
            }
        }
    }

    override suspend fun deleteVehicle(vehicle: Vehicle): Int {
        return when (vehicle) {
            is Car -> {
                val carEntity = VehicleTranslatorDomainToInfra.parseCarDomainToEntity(vehicle)
                parkingDatabase.carDAO.deleteCar(carEntity)
            }

            is Motorcycle -> {
                val motorcycleEntity =
                    VehicleTranslatorDomainToInfra.parseMotorcycleDomainToEntity(vehicle)
                parkingDatabase.motorcycleDAO.deleteMotorcycle(motorcycleEntity)
            }
            else -> 0
        }
    }*/

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