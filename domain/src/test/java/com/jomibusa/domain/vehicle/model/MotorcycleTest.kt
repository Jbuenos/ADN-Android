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

    @Test
    fun car_createMotorcycleWithBadPatterNumber_exception() {

        //Arrange
        val plate = Plate("123456")
        val expectedMessage = "Placa no cumple con el patrón requerido"

        //Act
        try {
            val car = Motorcycle(350, plate)
            Assert.fail()
        } catch (ex: Exception) {
            //Assert
            assertEquals(expectedMessage, ex.message)
        }
    }

    @Test
    fun car_createMotorcycleWithBadPatterLetters_exception() {

        //Arrange
        val plate = Plate("abcdef")
        val expectedMessage = "Placa no cumple con el patrón requerido"

        //Act
        try {
            val car = Motorcycle(250, plate)
            Assert.fail()
        } catch (ex: Exception) {
            //Assert
            assertEquals(expectedMessage, ex.message)
        }
    }

    @Test
    fun car_createMotorcycleWithBadPatterNumberAndLetters_exception() {

        //Arrange
        val plate = Plate("a3419c")
        val expectedMessage = "Placa no cumple con el patrón requerido"

        //Act
        try {
            val car = Motorcycle(300, plate)
            Assert.fail()
        } catch (ex: Exception) {
            //Assert
            assertEquals(expectedMessage, ex.message)
        }
    }

    @Test
    fun motorcycle_createMotorcycleWithBadPatterSpecialCharacter_exception() {

        //Arrange
        val plate = Plate("#bc*$")
        val expectedMessage = "Placa no cumple con el patrón requerido"

        //Act
        try {
            val motorcycle = Motorcycle(250, plate)
            Assert.fail()
        } catch (ex: Exception) {
            //Assert
            assertEquals(expectedMessage, ex.message)
        }
    }

}