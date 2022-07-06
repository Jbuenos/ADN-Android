package com.jomibusa.infrastructure.vehicle.anticorruption

import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.infrastructure.vehicle.entities.CarEntity
import com.jomibusa.infrastructure.vehicle.entities.MotorcycleEntity
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class VehicleTranslatorInfraToDomainTest {

    @Test
    fun parseCarEntityToDomain() {
        //Act
        val carEntity = CarEntity("HMT251")
        val parseCar = Car(Plate(carEntity.numPlate))

        //Act
        val result = VehicleTranslatorInfraToDomain.parseCarEntityToDomain(carEntity)

        //Assert
        assertThat(
            result.plate,
            CoreMatchers.equalTo(parseCar.plate)
        )
    }

    @Test
    fun parseMotorcycleEntityToDomain() {
        //Act
        val motorcycleEntity = MotorcycleEntity("HMT251", 250)
        val parseMotorcycle =
            Motorcycle(motorcycleEntity.cylinderCapacity, Plate(motorcycleEntity.numPlate))

        //Act
        val result = VehicleTranslatorInfraToDomain.parseMotorcycleEntityToDomain(motorcycleEntity)

        //Assert
        assertThat(
            result.plate,
            equalTo(parseMotorcycle.plate)
        )
    }

}