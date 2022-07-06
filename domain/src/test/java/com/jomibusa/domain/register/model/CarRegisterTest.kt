package com.jomibusa.domain.register.model

import com.jomibusa.domain.register.exception.FirstLetterOrDayConditionException
import com.jomibusa.domain.vehicle.exception.InvalidPatternPlateException
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDate
import java.util.*

class CarRegisterTest {

    @Test
    fun carRegister_createCarRegisterWithCorrectInformation_successful() {

        //Arrange
        val plate = Plate("JRP310")

        //Act
        val carRegister = CarRegister(Car(plate), Date())

        //Assert
        assertNotNull(carRegister)

    }

    @Test
    fun carRegister_createCarRegisterWithPlateWithA_exception() {

        //Arrange
        val plate = Plate("ABS190")

        //Act
        //Assert
        assertThrows(FirstLetterOrDayConditionException::class.java) {
            CarRegister(Car(plate), Date(1656781429))
        }
    }

    @Test
    fun carRegister_createCarRegisterWithDaySunday_exception() {

        //Arrange
        val plate = Plate("ABS190")

        //Act
        //Assert
        assertThrows(FirstLetterOrDayConditionException::class.java) {
            CarRegister(Car(plate), Date(1656954229))
        }
    }

}