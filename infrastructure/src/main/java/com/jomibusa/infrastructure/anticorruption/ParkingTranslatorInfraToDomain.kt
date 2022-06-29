package com.jomibusa.infrastructure.anticorruption

import com.jomibusa.domain.aggregate.Parking
import com.jomibusa.domain.entity.Car
import com.jomibusa.domain.entity.MotorCycle
import com.jomibusa.domain.exception.InfraToDomainException
import com.jomibusa.domain.valueObject.Plate
import com.jomibusa.infrastructure.entities.ParkingEntity
import java.util.*

class ParkingTranslatorInfraToDomain {

    fun parseInfraToDomain(parkingEntity: ParkingEntity): Parking {

        val vehicleEntity = if (parkingEntity.carEntity != null) {
            Car(Plate(parkingEntity.carEntity.numPlate))
        } else if (parkingEntity.motorCycleEntity != null) {
            MotorCycle(
                parkingEntity.motorCycleEntity.cylinderCapacity,
                Plate(parkingEntity.motorCycleEntity.numPlate)
            )
        } else {
            throw InfraToDomainException()
        }

        return Parking(vehicleEntity, Date(parkingEntity.date))
    }

    fun parseInfraToDomainList(listParkingEntity: List<ParkingEntity>): List<Parking> {
        val listDomain: MutableList<Parking> = mutableListOf()

        for (parkingEntity in listParkingEntity) {
            val item = parseInfraToDomain(parkingEntity)
            listDomain.add(item)
        }

        return listDomain
    }

}