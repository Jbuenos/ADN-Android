package com.jomibusa.domain.vehicle.model

import com.jomibusa.domain.vehicle.exception.InvalidPatternPlateException
import com.jomibusa.domain.vehicle.exception.NegativeCylinderCapacityException
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThrows
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

        //Act
        //Assert
        assertThrows(InvalidPatternPlateException::class.java) {
            Motorcycle(500, plate)
        }
    }

    @Test
    fun motorcycle_createMotorcycleWithNegativeCylinderCapacity_exception() {

        //Arrange
        val plate = Plate("UPA19C")

        //Act
        //Assert
        assertThrows(NegativeCylinderCapacityException::class.java) {
            Motorcycle(-500, plate)
        }
    }

    @Test
    fun car_createMotorcycleWithBadPatterNumber_exception() {

        //Arrange
        val plate = Plate("123456")

        //Act
        //Assert
        assertThrows(InvalidPatternPlateException::class.java) {
            Motorcycle(500, plate)
        }
    }

    @Test
    fun car_createMotorcycleWithBadPatterLetters_exception() {

        //Arrange
        val plate = Plate("abcdef")

        //Act
        //Assert
        assertThrows(InvalidPatternPlateException::class.java) {
            Motorcycle(350, plate)
        }
    }

    @Test
    fun car_createMotorcycleWithBadPatterNumberAndLetters_exception() {

        //Arrange
        val plate = Plate("a3419c")

        //Act
        //Assert
        assertThrows(InvalidPatternPlateException::class.java) {
            Motorcycle(250, plate)
        }
    }

    @Test
    fun motorcycle_createMotorcycleWithBadPatterSpecialCharacter_exception() {

        //Arrange
        val plate = Plate("#bc*$")

        //Act
        //Assert
        assertThrows(InvalidPatternPlateException::class.java) {
            Motorcycle(300, plate)
        }
    }

}