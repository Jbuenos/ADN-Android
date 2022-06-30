package com.jomibusa.infrastructure.vehicle.repository

import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.infrastructure.vehicle.anticorruption.ParkingTranslatorDomainToInfra
import com.jomibusa.infrastructure.vehicle.anticorruption.ParkingTranslatorInfraToDomain
import com.jomibusa.infrastructure.database.ParkingDatabase

class RoomParkingRepository(
    private val parkingDatabase: ParkingDatabase,
    private val parkingTranslatorDomainToInfra: ParkingTranslatorDomainToInfra,
    private val parkingTranslatorInfraToDomain: ParkingTranslatorInfraToDomain
) : RegisterRepository {

    override suspend fun insertRegister(register: Register) {
        val parkingEntity = parkingTranslatorDomainToInfra.parseDomainToEntity(register)
        if (parkingEntity != null) {
            parkingDatabase.parkingDAO.insertVehicleParking(parkingEntity)
        }
    }

    override suspend fun findRegisterByPlate(plate: Plate): Register? {
        val parkingEntity = parkingDatabase.parkingDAO.findVehicleByPlate(plate.numPlate)
        val parkingDomain = parkingEntity?.let {
            parkingTranslatorInfraToDomain.parseInfraToDomain(
                it
            )
        }
        return parkingDomain
    }

    override suspend fun getAllRegister(): List<Register> {
        val allVehicleFromParking = parkingDatabase.parkingDAO.getAllIVehiclesParking()
        var listDomain: List<Register> = listOf()
        allVehicleFromParking?.let {
            listDomain = parkingTranslatorInfraToDomain.parseInfraToDomainList(it)
        }
        return listDomain
    }

    /*override suspend fun deleteRegisterByPlate(plate: Plate): Boolean {
        return parkingDatabase.parkingDAO.deleteVehicleParking(plate.numPlate) != -1
    }*/

    /*override suspend fun getNumOfCars(): Int? {
        return parkingDatabase.carDAO.getAllCars()?.size
    }

    override suspend fun getNumOfMotorcycles(): Int? {
        return parkingDatabase.motorcycleDAO.getAllMotorcycle()?.size
    }*/

}