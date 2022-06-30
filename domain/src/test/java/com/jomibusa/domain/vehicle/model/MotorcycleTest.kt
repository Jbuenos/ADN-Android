package com.jomibusa.domain.vehicle.model

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class MotorcycleTest {

    @Test
    fun motorcycle_createMotorcycleWithCorrectPlate_successful() {

        //Arrange
        val plate = Plate("JRP304")
        val cylinderCapacity = 450

        //Act
        val expected = Motorcycle(cylinderCapacity, plate)

        //Assert
        assertNotNull(expected)
    }

    @Test
    fun motorcycle_createMotorcycleWithNullPlate_exception() {

        //Arrange
        val plate = Plate("")
        val expectedMessage = "La Placa del vehiculo está en blanco"

        //Act
        try {
            val motorCycle = Motorcycle(500, plate)
            Assert.fail()
        } catch (ex: Exception) {
            //Assert
            assertEquals(expectedMessage, ex.message)
        }
    }

    @Test
    fun motorcycle_createMotorcycleWithNegativeCylinderCapacity_exception() {

        //Arrange
        val plate = Plate("UPA19C")
        val expectedMessage = "El cilindraje no puede ser negativo"

        //Act
        try {
            val motorCycle = Motorcycle(-500, plate)
            Assert.fail()
        } catch (ex: Exception) {
            //Assert
            assertEquals(expectedMessage, ex.message)
        }
    }

}