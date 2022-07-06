package com.jomibusa.infrastructure.register.repository

import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.model.MotorcycleRegister
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.infrastructure.register.anticorruption.RegisterTranslatorDomainToInfra
import com.jomibusa.infrastructure.register.anticorruption.RegisterTranslatorInfraToDomain
import com.jomibusa.infrastructure.shared.database.ParkingDatabase

class RoomRegisterParkingRepository(
    private val parkingDatabase: ParkingDatabase,
    private val registerParkingTranslatorDomainToInfra: RegisterTranslatorDomainToInfra,
    private val registerParkingTranslatorInfraToDomain: RegisterTranslatorInfraToDomain
) : RegisterRepository {

    override suspend fun insertRegister(register: Register) {
        val registerEntity =
            registerParkingTranslatorDomainToInfra.parseParkingRegisterDomainToEntity(register)
        parkingDatabase.parkingRegisterDAO.insertParkingRegister(registerEntity)
    }

    /*override suspend fun findCarRegisterByPlate(plate: Plate): CarRegister? {
        val registerEntity = parkingDatabase.carDAO.findCarAndRegisterByPlate(plate.numPlate)
        return if (registerEntity != null) {
            registerParkingTranslatorInfraToDomain.parseParkingRegisterCarEntityToDomain(
                registerEntity
            )
        } else {
            null
        }
    }

    override suspend fun findMotorcycleRegisterByPlate(plate: Plate): MotorcycleRegister? {
        val registerEntity =
            parkingDatabase.motorcycleDAO.findMotorcycleAndRegisterByPlate(plate.numPlate)
        return if (registerEntity != null) {
            registerParkingTranslatorInfraToDomain.parseParkingRegisterMotorcycleEntityToDomain(
                registerEntity
            )
        } else {
            null
        }
    }

    private fun getAllCarsRegister(): List<CarRegister> {
        val allRegisterFromParking = parkingDatabase.carDAO.getAllCarsAndRegisterFromParking()
        var listDomain: List<CarRegister> = listOf()
        allRegisterFromParking?.let {
            listDomain =
                registerParkingTranslatorInfraToDomain.parseParkingRegisterCarEntityToDomain(it)
        }
        return listDomain
    }

    override suspend fun getAllMotorcyclesRegister(): List<MotorcycleRegister> {
        val allRegisterFromParking =
            parkingDatabase.motorcycleDAO.getAllMotorcyclesAndRegisterFromParking()
        var listDomain: List<MotorcycleRegister> = listOf()
        allRegisterFromParking?.let {
            listDomain =
                registerParkingTranslatorInfraToDomain.parseParkingRegisterMotorcycleEntityToDomain(
                    it
                )
        }
        return listDomain
    }*/

    override suspend fun getAllRegisters(): List<Register>? {
        TODO("Not yet implemented")
    }

    override suspend fun findRegisterByPlate(plate: Plate): Register? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRegister(register: Register): Int {
        val parkingRegisterEntity =
            registerParkingTranslatorDomainToInfra.parseParkingRegisterDomainToEntity(register)
        return parkingDatabase.parkingRegisterDAO.deleteParkingRegister(parkingRegisterEntity)
    }
}