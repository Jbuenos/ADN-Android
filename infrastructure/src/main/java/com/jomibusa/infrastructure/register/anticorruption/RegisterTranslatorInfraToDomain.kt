package com.jomibusa.infrastructure.register.anticorruption

import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.model.MotorcycleRegister
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.domain.vehicle.model.Vehicle
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity
import com.jomibusa.infrastructure.shared.relation.ParkingRegisterWithCars
import com.jomibusa.infrastructure.shared.relation.ParkingRegisterWithMotorcycle
import java.util.*

object RegisterTranslatorInfraToDomain {

    fun parseParkingRegisterCarEntityToDomain(registerWithCars: ParkingRegisterWithCars): CarRegister =
        CarRegister(
            Car(Plate(registerWithCars.carEntity.numPlate)),
            Date(registerWithCars.parkingRegisterEntity.initDate)
        )

    fun parseParkingRegisterMotorcycleEntityToDomain(registerWithMotorcycle: ParkingRegisterWithMotorcycle): MotorcycleRegister =
        MotorcycleRegister(
            Motorcycle(
                registerWithMotorcycle.motorcycleEntity.cylinderCapacity,
                Plate(registerWithMotorcycle.motorcycleEntity.numPlate)
            ),
            Date(registerWithMotorcycle.parkingRegisterEntity.initDate)
        )

    fun parseParkingRegisterCarEntityToDomain(listRegisterWithCars: List<ParkingRegisterWithCars>): List<CarRegister> {
        val listRegister: MutableList<CarRegister> = mutableListOf()
        listRegisterWithCars.forEach {
            listRegister.add(
                CarRegister(
                    Car(Plate(it.carEntity.numPlate)),
                    Date(it.parkingRegisterEntity.initDate)
                )
            )
        }

        return listRegister
    }

    fun parseParkingRegisterMotorcycleEntityToDomain(listRegisterWithMotorcycle: List<ParkingRegisterWithMotorcycle>): List<MotorcycleRegister> {
        val listRegister: MutableList<MotorcycleRegister> = mutableListOf()
        listRegisterWithMotorcycle.forEach {
            listRegister.add(
                MotorcycleRegister(
                    Motorcycle(
                        it.motorcycleEntity.cylinderCapacity,
                        Plate(it.motorcycleEntity.numPlate)
                    ),
                    Date(it.parkingRegisterEntity.initDate)
                )
            )
        }

        return listRegister
    }

}