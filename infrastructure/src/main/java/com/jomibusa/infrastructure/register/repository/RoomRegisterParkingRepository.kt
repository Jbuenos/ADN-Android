package com.jomibusa.infrastructure.register.repository

import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.infrastructure.register.anticorruption.RegisterTranslatorDomainToInfra
import com.jomibusa.infrastructure.vehicle.anticorruption.VehicleTranslatorDomainToInfra
import com.jomibusa.infrastructure.vehicle.anticorruption.VehicleTranslatorInfraToDomain
import com.jomibusa.infrastructure.shared.database.ParkingDatabase

class RoomRegisterParkingRepository(
    private val parkingDatabase: ParkingDatabase,
    private val vehicleTranslatorDomainToInfra: VehicleTranslatorDomainToInfra,
    private val vehicleTranslatorInfraToDomain: VehicleTranslatorInfraToDomain,
    private val registerParkingTranslatorDomainToInfra: RegisterTranslatorDomainToInfra
) : RegisterRepository {

    override suspend fun insertRegister(register: Register) {

        val registerEntity =
            registerParkingTranslatorDomainToInfra.parseParkingRegisterDomainToEntity(register)

        when (register.vehicle) {
            is Car -> {
                val carEntity =
                    vehicleTranslatorDomainToInfra.parseCarDomainToEntity(register.vehicle as Car)
                parkingDatabase.carDAO.insertCar(carEntity)
                parkingDatabase.parkingRegisterDAO.insertParkingRegister(registerEntity)
            }
            is Motorcycle -> {
                val motorcycleEntity =
                    vehicleTranslatorDomainToInfra.parseMotorcycleDomainToEntity(register.vehicle as Motorcycle)
                parkingDatabase.motorcycleDAO.insertMotorcycle(motorcycleEntity)
                parkingDatabase.parkingRegisterDAO.insertParkingRegister(registerEntity)
            }
        }
    }

    override suspend fun findRegisterByPlate(plate: Plate): Register? {
        TODO()
        /*val parkingEntity = parkingDatabase.parkingDAO.findVehicleByPlate(plate.numPlate)
        val parkingDomain = parkingEntity?.let {
            parkingTranslatorInfraToDomain.parseInfraToDomain(
                it
            )
        }
        return parkingDomain*/
    }

    override suspend fun getAllRegister(): List<Register> {
        //val allVehicleFromParking = parkingDatabase.parkingDAO.getAllIVehiclesParking()
        var listDomain: List<Register> = listOf()
        /*allVehicleFromParking?.let {
            listDomain = vehicleTranslatorInfraToDomain.parseInfraToDomainList(it)
        }*/
        return listDomain
    }

    override suspend fun deleteRegisterByPlate(plate: Plate): Int {
        TODO()
        //return parkingDatabase.parkingDAO.deleteVehicleParking(plate.numPlate)
    }

    /*override suspend fun getNumOfCars(): Int? {
        return parkingDatabase.carDAO.getAllCars()?.size
    }

    override suspend fun getNumOfMotorcycles(): Int? {
        return parkingDatabase.motorcycleDAO.getAllMotorcycle()?.size
    }*/

}