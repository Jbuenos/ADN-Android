package com.jomibusa.infrastructure.register.repository

import com.jomibusa.domain.register.model.MotorcycleRegister
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.infrastructure.register.anticorruption.RegisterTranslatorDomainToInfra
import com.jomibusa.infrastructure.register.anticorruption.RegisterTranslatorInfraToDomain
import com.jomibusa.infrastructure.register.exception.DeleteEntityDatabaseException
import com.jomibusa.infrastructure.shared.database.ParkingDatabase
import com.jomibusa.infrastructure.vehicle.anticorruption.VehicleTranslatorDomainToInfra

class RoomMotorcycleParkingRegisterRepository(private val parkingDatabase: ParkingDatabase) :
    RegisterRepository {

    override suspend fun insertRegister(register: Register) {
        val registerEntity =
            RegisterTranslatorDomainToInfra.parseParkingRegisterDomainToEntity(register)
        val motorcycleEntity =
            VehicleTranslatorDomainToInfra.parseMotorcycleDomainToEntity(register.vehicle as Motorcycle)
        parkingDatabase.parkingRegisterDAO.insertParkingRegister(registerEntity)
        parkingDatabase.motorcycleDAO.insertMotorcycle(motorcycleEntity)
    }

    override suspend fun getAllRegisters(): List<Register> {
        val allRegisterFromParking =
            parkingDatabase.motorcycleDAO.getAllMotorcyclesAndRegisterFromParking()
        var listDomain: List<MotorcycleRegister> = listOf()
        allRegisterFromParking?.let {
            listDomain =
                RegisterTranslatorInfraToDomain.parseParkingRegisterMotorcycleEntityToDomain(
                    it
                )
        }
        return listDomain
    }

    override suspend fun findRegisterByPlate(plate: Plate): Register? {
        val registerEntity =
            parkingDatabase.motorcycleDAO.findMotorcycleAndRegisterByPlate(plate.numPlate)
        return if (registerEntity != null) {
            RegisterTranslatorInfraToDomain.parseParkingRegisterMotorcycleEntityToDomain(
                registerEntity
            )
        } else {
            null
        }
    }

    override suspend fun deleteRegister(register: Register): Int {
        val parkingRegisterEntity =
            RegisterTranslatorDomainToInfra.parseParkingRegisterDomainToEntity(register)
        val motorcycleEntity =
            VehicleTranslatorDomainToInfra.parseMotorcycleDomainToEntity(register.vehicle as Motorcycle)
        val deleteMotorcycle = parkingDatabase.motorcycleDAO.deleteMotorcycle(motorcycleEntity)
        val deleteParkingRegister =
            parkingDatabase.parkingRegisterDAO.deleteParkingRegister(parkingRegisterEntity)
        if (deleteMotorcycle == -1 || deleteParkingRegister == -1) throw  DeleteEntityDatabaseException()
        return deleteParkingRegister
    }
}