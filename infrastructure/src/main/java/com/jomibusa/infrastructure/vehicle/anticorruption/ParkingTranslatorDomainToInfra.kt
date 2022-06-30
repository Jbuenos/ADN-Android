package com.jomibusa.infrastructure.vehicle.anticorruption

import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.infrastructure.vehicle.entities.CarEntity
import com.jomibusa.infrastructure.vehicle.entities.MotorcycleEntity
import com.jomibusa.infrastructure.vehicle.entities.ParkingEntity

class ParkingTranslatorDomainToInfra {

    fun parseDomainToEntity(register: Register): ParkingEntity? {

        return when (register.vehicle) {
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
        }
    }

}