package com.jomibusa.domain.vehicle.model

import com.jomibusa.domain.vehicle.exception.InvalidPatternPlateException
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThrows
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

        //Act
        //Assert
        assertThrows(InvalidPatternPlateException::class.java) {
            Car(plate)
        }
    }

    @Test
    fun car_createCarWithBadPatterNumber_exception() {

        //Arrange
        val plate = Plate("123456")

        //Act
        //Assert
        assertThrows(InvalidPatternPlateException::class.java) {
            Car(plate)
        }
    }

    @Test
    fun car_createCarWithBadPatterLetters_exception() {

        //Arrange
        val plate = Plate("abcdef")

        //Act
        //Assert
        assertThrows(InvalidPatternPlateException::class.java) {
            Car(plate)
        }
    }

    @Test
    fun car_createCarWithBadPatterNumberAndLetters_exception() {

        //Arrange
        val plate = Plate("a3419c")

        //Act
        //Assert
        assertThrows(InvalidPatternPlateException::class.java) {
            Car(plate)
        }
    }

    @Test
    fun car_createCarWithBadPatterSpecialCharacter_exception() {

        //Arrange
        val plate = Plate("#bc*$")

        //Act
        //Assert
        assertThrows(InvalidPatternPlateException::class.java) {
            Car(plate)
        }
    }

}