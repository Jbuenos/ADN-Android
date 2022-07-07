package com.jomibusa.domain.register.model

import com.jomibusa.domain.register.exception.FirstLetterOrDayConditionException
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThrows
import org.junit.Test
import java.util.*

class MotorcycleRegisterTest {

    @Test
    fun motorcycleRegister_createMotorcycleRegisterWithCorrectInformation_successful() {

        //Arrange
        val plate = Plate("JRP310")

        //Act
        val motorcycleRegister = MotorcycleRegister(Motorcycle(400, plate), Date())

        //Assert
        assertNotNull(motorcycleRegister)

    }

    @Test
    fun motorcycleRegister_createMotorcycleRegisterWithPlateWithA_exception() {

        //Arrange
        val plate = Plate("ABS190")

        //Act
        //Assert
        assertThrows(FirstLetterOrDayConditionException::class.java) {
            MotorcycleRegister(Motorcycle(450, plate), Date(1656781429))
        }
    }

    @Test
    fun motorcycleRegister_createCarRegisterWithDaySunday_exception() {

        //Arrange
        val plate = Plate("ABS190")

        //Act
        //Assert
        assertThrows(FirstLetterOrDayConditionException::class.java) {
            MotorcycleRegister(Motorcycle(450, plate), Date(1656954229))
        }
    }

}