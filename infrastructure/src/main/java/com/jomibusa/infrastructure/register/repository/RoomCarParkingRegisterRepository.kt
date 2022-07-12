package com.jomibusa.infrastructure.register.repository

import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.infrastructure.register.anticorruption.RegisterTranslatorDomainToInfra
import com.jomibusa.infrastructure.register.anticorruption.RegisterTranslatorInfraToDomain
import com.jomibusa.infrastructure.register.dao.ParkingRegisterDAO
import com.jomibusa.infrastructure.shared.relation.ParkingRegisterWithCar
import com.jomibusa.infrastructure.vehicle.anticorruption.VehicleTranslatorDomainToInfra
import javax.inject.Inject

class RoomCarParkingRegisterRepository @Inject constructor(private val parkingRegisterDAO: ParkingRegisterDAO) :
    RegisterRepository {

    override suspend fun getAllRegisters(): List<Register> {
        val allRegisterFromParking = parkingRegisterDAO.getAllCarsAndRegisterFromParking()
        var listDomain: List<CarRegister> = listOf()
        allRegisterFromParking?.let {
            listDomain =
                RegisterTranslatorInfraToDomain.parseParkingRegisterCarEntityToDomain(it)
        }
        return listDomain
    }

    override suspend fun findRegisterByPlate(plate: Plate): Register? {
        val registerEntity = parkingRegisterDAO.findCarAndRegisterByPlate(plate.numPlate)
        return if (registerEntity != null) {
            RegisterTranslatorInfraToDomain.parseParkingRegisterCarEntityToDomain(
                registerEntity
            )
        } else {
            null
        }
    }

    private fun getParkingRegisterWithCar(register: Register): ParkingRegisterWithCar {
        return ParkingRegisterWithCar(
            VehicleTranslatorDomainToInfra.parseCarDomainToEntity(register.vehicle as Car),
            RegisterTranslatorDomainToInfra.parseParkingRegisterDomainToEntity(register)
        )
    }

    override suspend fun insertRegister(register: Register) {
        parkingRegisterDAO.saveRegisterWithCar(getParkingRegisterWithCar(register))
    }

    override suspend fun deleteRegister(register: Register): Int {
        return parkingRegisterDAO.deleteRegisterWithCar(getParkingRegisterWithCar(register))
    }
}