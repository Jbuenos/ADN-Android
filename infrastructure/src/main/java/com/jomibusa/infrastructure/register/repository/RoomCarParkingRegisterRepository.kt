package com.jomibusa.infrastructure.register.repository

import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.infrastructure.register.anticorruption.RegisterTranslatorDomainToInfra
import com.jomibusa.infrastructure.register.anticorruption.RegisterTranslatorInfraToDomain
import com.jomibusa.infrastructure.shared.database.ParkingDatabase
import com.jomibusa.infrastructure.vehicle.anticorruption.VehicleTranslatorDomainToInfra

class RoomCarParkingRegisterRepository(private val parkingDatabase: ParkingDatabase) :
    RegisterRepository {

    override suspend fun getAllRegisters(): List<Register> {
        val allRegisterFromParking = parkingDatabase.carDAO.getAllCarsAndRegisterFromParking()
        var listDomain: List<CarRegister> = listOf()
        allRegisterFromParking?.let {
            listDomain =
                RegisterTranslatorInfraToDomain.parseParkingRegisterCarEntityToDomain(it)
        }
        return listDomain
    }

    override suspend fun findRegisterByPlate(plate: Plate): Register? {
        val registerEntity = parkingDatabase.carDAO.findCarAndRegisterByPlate(plate.numPlate)
        return if (registerEntity != null) {
            RegisterTranslatorInfraToDomain.parseParkingRegisterCarEntityToDomain(
                registerEntity
            )
        } else {
            null
        }
    }

    override suspend fun insertRegister(register: Register) {
        val registerEntity =
            RegisterTranslatorDomainToInfra.parseParkingRegisterDomainToEntity(register)
        val carEntity =
            VehicleTranslatorDomainToInfra.parseCarDomainToEntity(register.vehicle as Car)
        parkingDatabase.parkingRegisterDAO.insertParkingRegister(registerEntity)
        parkingDatabase.carDAO.insertCar(carEntity)
    }

    override suspend fun deleteRegister(register: Register): Int {
        val parkingRegisterEntity =
            RegisterTranslatorDomainToInfra.parseParkingRegisterDomainToEntity(register)
        val carEntity =
            VehicleTranslatorDomainToInfra.parseCarDomainToEntity(register.vehicle as Car)
        parkingDatabase.carDAO.deleteCar(carEntity)
        return parkingDatabase.parkingRegisterDAO.deleteParkingRegister(parkingRegisterEntity)
    }
}