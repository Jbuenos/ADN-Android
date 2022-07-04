package com.jomibusa.infrastructure.vehicle.anticorruption

import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.infrastructure.vehicle.entities.CarEntity
import com.jomibusa.infrastructure.vehicle.entities.MotorcycleEntity

class VehicleTranslatorInfraToDomain {

    fun parseCarEntityToDomain(carEntity: CarEntity): Car = Car(Plate(carEntity.numPlate))


    fun parseMotorcycleEntityToDomain(motorcycleEntity: MotorcycleEntity): Motorcycle =
        Motorcycle(motorcycleEntity.cylinderCapacity, Plate(motorcycleEntity.numPlate))

    fun parseCarEntityToDomain(listCarEntity: List<CarEntity>): List<Car> {
        val listCars: MutableList<Car> = mutableListOf()
        listCarEntity.forEach {
            listCars.add(Car(Plate(it.numPlate)))
        }

        return listCars
    }

    fun parseMotorcycleEntityToDomain(listMotorcycleEntity: List<MotorcycleEntity>): List<Motorcycle> {
        val listMotorcycle: MutableList<Motorcycle> = mutableListOf()
        listMotorcycleEntity.forEach {
            listMotorcycle.add(Motorcycle(it.cylinderCapacity, Plate(it.numPlate)))
        }

        return listMotorcycle
    }

}