package com.jomibusa.domain.entity

import com.jomibusa.domain.valueObject.Plate
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class MotorCycleTest {

    @Test
    fun motorcycle_createMotorcycleWithCorrectPlate_successful() {

        //Arrange
        val plate = Plate("JRP304")
        val cylinderCapacity = 450

        //Act
        val expected = MotorCycle(cylinderCapacity, plate)

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
            val motorCycle = MotorCycle(500, plate)
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
            val motorCycle = MotorCycle(-500, plate)
            Assert.fail()
        } catch (ex: Exception) {
            //Assert
            assertEquals(expectedMessage, ex.message)
        }
    }

}