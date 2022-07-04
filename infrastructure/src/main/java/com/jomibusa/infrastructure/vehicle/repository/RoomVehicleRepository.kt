package com.jomibusa.infrastructure.vehicle.repository

import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Vehicle
import com.jomibusa.domain.vehicle.repository.CarRepository
import com.jomibusa.domain.vehicle.repository.MotorcycleRepository
import com.jomibusa.infrastructure.shared.database.ParkingDatabase
import com.jomibusa.infrastructure.vehicle.anticorruption.VehicleTranslatorDomainToInfra
import com.jomibusa.infrastructure.vehicle.anticorruption.VehicleTranslatorInfraToDomain

class RoomVehicleRepository(
    private val parkingDatabase: ParkingDatabase,
    private val vehicleTranslatorDomainToInfra: VehicleTranslatorDomainToInfra,
    private val vehicleTranslatorInfraToDomain: VehicleTranslatorInfraToDomain
) : CarRepository, MotorcycleRepository {

    override suspend fun insertVehicle(register: Register) {

        when (register.vehicle) {
            is Car -> {
                val carEntity =
                    vehicleTranslatorDomainToInfra.parseCarDomainToEntity(register.vehicle as Car)
                parkingDatabase.carDAO.insertCar(carEntity)
            }
            is Motorcycle -> {
                val motorcycleEntity =
                    vehicleTranslatorDomainToInfra.parseMotorcycleDomainToEntity(register.vehicle as Motorcycle)
                parkingDatabase.motorcycleDAO.insertMotorcycle(motorcycleEntity)
            }
        }
    }

    override suspend fun getAllCars(): List<Car> {
        val allCarsFromParking = parkingDatabase.carDAO.getAllCarsFromParking()
        var listDomain: List<Car> = listOf()
        allCarsFromParking?.let {
            listDomain = vehicleTranslatorInfraToDomain.parseCarEntityToDomain(it)
        }
        return listDomain
    }

    override suspend fun getAllMotorcycles(): List<Motorcycle> {
        val allMotorcyclesFromParking = parkingDatabase.motorcycleDAO.getAllMotorcyclesFromParking()
        var listDomain: List<Motorcycle> = listOf()
        allMotorcyclesFromParking?.let {
            listDomain = vehicleTranslatorInfraToDomain.parseMotorcycleEntityToDomain(it)
        }
        return listDomain
    }

    override suspend fun deleteVehicle(vehicle: Vehicle): Int {
        return when (vehicle) {
            is Car -> {
                val carEntity = vehicleTranslatorDomainToInfra.parseCarDomainToEntity(vehicle)
                parkingDatabase.carDAO.deleteCar(carEntity)
            }

            is Motorcycle -> {
                val motorcycleEntity =
                    vehicleTranslatorDomainToInfra.parseMotorcycleDomainToEntity(vehicle)
                parkingDatabase.motorcycleDAO.deleteMotorcycle(motorcycleEntity)
            }
            else -> 0
        }
    }

    override suspend fun findVehicleByPlate(register: Register): Vehicle? {
        return when (register.vehicle) {
            is Car -> {
                val carEntity = parkingDatabase.carDAO.findCarByPlate(register.vehicle.plate.numPlate)
                carEntity?.let {
                    vehicleTranslatorInfraToDomain.parseCarEntityToDomain(it)
                }
            }

            is Motorcycle -> {
                val motorcycleEntity =
                    parkingDatabase.motorcycleDAO.findMotorcycleByPlate(register.vehicle.plate.numPlate)
                motorcycleEntity?.let {
                    vehicleTranslatorInfraToDomain.parseMotorcycleEntityToDomain(it)
                }
            }

            else -> null
        }
    }

}