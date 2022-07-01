package com.jomibusa.infrastructure.vehicle.anticorruption

import com.jomibusa.domain.register.model.Register
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity

class ParkingTranslatorDomainToInfra {

    fun parseDomainToEntity(register: Register): ParkingRegisterEntity? {

        /*return when (register.vehicle) {
            is Car -> {
                ParkingEntity(
                    carEntity = CarEntity(register.vehicle.plate.numPlate),
                    date = register.initDate.time
                )
            }
            is Motorcycle -> {
                ParkingEntity(
                    motorcycleEntity = MotorcycleEntity(
                        register.vehicle.plate.numPlate,
                        (register.vehicle as Motorcycle).cylinderCapacity
                    ),
                    date = register.initDate.time
                )
            }
            else -> null
        }*/
        TODO()
    }

}