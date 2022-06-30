package com.jomibusa.domain.vehicle.model

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class CarTest {

    @Test
    fun car_createCarWithCorrectPlate_successful() {

        //Arrange
        val plate = Plate("JRP304")

        //Act
        val expected = Car(plate)

        //Assert
        assertNotNull(expected)
    }

    @Test
    fun car_createCarWithNullPlate_exception() {

        //Arrange
        val plate = Plate("")
        val expectedMessage = "La Placa del vehiculo está en blanco"

        //Act
        try {
            val car = Car(plate)
            Assert.fail()
        } catch (ex: Exception) {
            //Assert
            assertEquals(expectedMessage, ex.message)
        }
    }

}