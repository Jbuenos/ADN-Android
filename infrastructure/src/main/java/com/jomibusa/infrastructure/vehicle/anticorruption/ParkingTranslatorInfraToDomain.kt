package com.jomibusa.infrastructure.vehicle.anticorruption

import com.jomibusa.domain.register.model.Register
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity

class ParkingTranslatorInfraToDomain {

    fun parseInfraToDomain(parkingEntity: ParkingRegisterEntity): Register? {

        /*val vehicleEntity = if (parkingEntity.carEntity != null) {
            CarRegister(Plate(parkingEntity.carEntity.numPlate))
        } else if (parkingEntity.motorcycleEntity != null) {
            Motorcycle(
                parkingEntity.motorcycleEntity.cylinderCapacity,
                Plate(parkingEntity.motorcycleEntity.numPlate)
            )
        } else {
            throw InfraToDomainException()
        }*/

        return null
    }

    fun parseInfraToDomainList(listParkingEntity: List<ParkingRegisterEntity>): List<Register> {
        val listDomain: MutableList<Register> = mutableListOf()

        for (parkingEntity in listParkingEntity) {
            val item = parseInfraToDomain(parkingEntity)
            if (item != null) {
                listDomain.add(item)
            }
        }

        return listDomain
    }

}