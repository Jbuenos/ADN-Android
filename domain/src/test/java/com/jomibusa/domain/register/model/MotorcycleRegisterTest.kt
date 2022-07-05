package com.jomibusa.domain.register.model

import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import org.junit.Assert
import org.junit.Assert.*
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
        val expectedMessage = "Restricción para placas que comienza con A y es lunes o domingo"

        //Act
        try {
            val motorcycleRegister = MotorcycleRegister(Motorcycle(450, plate), Date(1656781429))
            Assert.fail()
        } catch (ex: Exception) {
            //Assert
            assertEquals(expectedMessage, ex.message)
        }
    }

    @Test
    fun motorcycleRegister_createCarRegisterWithDaySunday_exception() {

        //Arrange
        val plate = Plate("ABS190")
        val expectedMessage = "Restricción para placas que comienza con A y es lunes o domingo"

        //Act
        try {
            val motorcycleRegister = MotorcycleRegister(Motorcycle(450, plate), Date(1656954229))
            Assert.fail()
        } catch (ex: Exception) {
            //Assert
            assertEquals(expectedMessage, ex.message)
        }
    }

}