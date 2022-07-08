package com.jomibusa.infrastructure.register.repository

import com.jomibusa.domain.register.model.MotorcycleRegister
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.infrastructure.register.anticorruption.RegisterTranslatorDomainToInfra
import com.jomibusa.infrastructure.register.anticorruption.RegisterTranslatorInfraToDomain
import com.jomibusa.infrastructure.shared.database.ParkingDatabase
import com.jomibusa.infrastructure.shared.relation.ParkingRegisterWithMotorcycle
import com.jomibusa.infrastructure.vehicle.anticorruption.VehicleTranslatorDomainToInfra

class RoomMotorcycleParkingRegisterRepository(private val parkingDatabase: ParkingDatabase) :
    RegisterRepository {

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

    private fun getParkingRegisterWithMotorcycle(register: Register): ParkingRegisterWithMotorcycle {
        return ParkingRegisterWithMotorcycle(
            VehicleTranslatorDomainToInfra.parseMotorcycleDomainToEntity(register.vehicle as Motorcycle),
            RegisterTranslatorDomainToInfra.parseParkingRegisterDomainToEntity(register)
        )
    }

    override suspend fun insertRegister(register: Register) {
        parkingDatabase.parkingRegisterDAO.saveRegisterWithMotorcycle(
            getParkingRegisterWithMotorcycle(register)
        )
    }

    override suspend fun deleteRegister(register: Register) {
        parkingDatabase.parkingRegisterDAO.deleteRegisterWithMotorcycle(
            getParkingRegisterWithMotorcycle(register)
        )
    }
}