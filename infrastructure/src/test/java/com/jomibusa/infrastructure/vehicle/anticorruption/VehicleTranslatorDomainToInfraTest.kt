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

class VehicleTranslatorDomainToInfraTest {

    @Test
    fun parseCarDomainToEntity_shouldValuePlateBeTheSame() {
        //Arrange
        val car = Car(Plate("HMT251"))
        val parseCarEntity = CarEntity(car.plate.numPlate)

        //Act
        val result = VehicleTranslatorDomainToInfra.parseCarDomainToEntity(car)

        //Assert
        assertThat(
            result.numPlate,
            CoreMatchers.equalTo(parseCarEntity.numPlate)
        )
    }

    @Test
    fun parseMotorcycleDomainToEntity_shouldValuePlateBeTheSame() {
        //Arrange
        val motorcycle = Motorcycle(250, Plate("HMT251"))
        val parseMotorcycleEntity =
            MotorcycleEntity(motorcycle.plate.numPlate, motorcycle.cylinderCapacity)

        //Act
        val result = VehicleTranslatorDomainToInfra.parseMotorcycleDomainToEntity(motorcycle)

        //Assert
        assertThat(
            result.numPlate,
            equalTo(parseMotorcycleEntity.numPlate)
        )
    }

}