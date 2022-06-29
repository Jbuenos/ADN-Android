package com.jomibusa.infrastructure.anticorruption

import com.jomibusa.domain.aggregate.Parking
import com.jomibusa.domain.entity.MotorCycle
import com.jomibusa.domain.valueObject.TypeVehicle
import com.jomibusa.infrastructure.entities.CarEntity
import com.jomibusa.infrastructure.entities.MotorCycleEntity
import com.jomibusa.infrastructure.entities.ParkingEntity

class ParkingTranslatorDomainToInfra {

    fun parseDomainToEntity(parking: Parking): ParkingEntity {

       return if(parking.vehicle.typeVehicle == TypeVehicle.CAR) {
           ParkingEntity(
               carEntity = CarEntity(parking.vehicle.plate.numPlate),
               date = parking.date.time)
       } else {
           ParkingEntity(
               motorCycleEntity = MotorCycleEntity(
                   parking.vehicle.plate.numPlate,
                   (parking.vehicle as MotorCycle).cylinderCapacity
               ),
               date = parking.date.time)
       }
    }

}