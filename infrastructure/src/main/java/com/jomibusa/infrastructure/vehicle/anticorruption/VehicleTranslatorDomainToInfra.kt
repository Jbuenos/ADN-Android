package com.jomibusa.infrastructure.vehicle.anticorruption

import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Vehicle
import com.jomibusa.infrastructure.vehicle.entities.CarEntity
import com.jomibusa.infrastructure.vehicle.entities.MotorcycleEntity

object VehicleTranslatorDomainToInfra {

    fun parseCarDomainToEntity(car: Car): CarEntity = CarEntity(car.plate.numPlate)

    fun parseMotorcycleDomainToEntity(motorcycle: Motorcycle): MotorcycleEntity =
        MotorcycleEntity(motorcycle.numPlate.numPlate, motorcycle.cylinderCapacity)

}