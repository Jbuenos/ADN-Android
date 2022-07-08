package com.jomibusa.infrastructure.register.anticorruption

import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.model.MotorcycleRegister
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.infrastructure.shared.relation.ParkingRegisterWithCar
import com.jomibusa.infrastructure.shared.relation.ParkingRegisterWithMotorcycle
import java.util.*

object RegisterTranslatorInfraToDomain {

    fun parseParkingRegisterCarEntityToDomain(registerWithCar: ParkingRegisterWithCar): CarRegister =
        CarRegister(
            Car(Plate(registerWithCar.carEntity.numPlate)),
            Date(registerWithCar.parkingRegisterEntity.initDate)
        )

    fun parseParkingRegisterMotorcycleEntityToDomain(registerWithMotorcycle: ParkingRegisterWithMotorcycle): MotorcycleRegister =
        MotorcycleRegister(
            Motorcycle(
                registerWithMotorcycle.motorcycleEntity.cylinderCapacity,
                Plate(registerWithMotorcycle.motorcycleEntity.numPlate)
            ),
            Date(registerWithMotorcycle.parkingRegisterEntity.initDate)
        )

    fun parseParkingRegisterCarEntityToDomain(listRegisterWithCars: List<ParkingRegisterWithCar>): List<CarRegister> {
        val listRegister: MutableList<CarRegister> = mutableListOf()
        listRegisterWithCars.forEach {
            listRegister.add(
                parseParkingRegisterCarEntityToDomain(it)
            )
        }

        return listRegister
    }

    fun parseParkingRegisterMotorcycleEntityToDomain(listRegisterWithMotorcycle: List<ParkingRegisterWithMotorcycle>): List<MotorcycleRegister> {
        val listRegister: MutableList<MotorcycleRegister> = mutableListOf()
        listRegisterWithMotorcycle.forEach {
            listRegister.add(
                parseParkingRegisterMotorcycleEntityToDomain(it)
            )
        }

        return listRegister
    }

}