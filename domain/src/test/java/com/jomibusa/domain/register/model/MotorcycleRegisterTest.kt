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
    fun motorcycle_createMotorcycleRegisterWithCorrectInformation_successful() {

        //Arrange
        val plate = Plate("JRP310")

        //Act
        val motorcycleRegister = MotorcycleRegister(Motorcycle(400, plate), Date())

        //Assert
        assertNotNull(motorcycleRegister)

    }

    @Test
    fun motorcycle_createMotorcycleRegisterWithPlateWithA_exception() {

        //Arrange
        val plate = Plate("APA19C")
        //var date = LocalDate.of(2018, 12, 31)
        val expectedMessage = "Restricción para placas que comienza con A y es lunes o domingo"

        //Act
        try {
            val motorcycleRegister = MotorcycleRegister(Motorcycle(450, plate), Date())
            Assert.fail()
        } catch (ex: Exception) {
            //Assert
            assertEquals(expectedMessage, ex.message)
        }
    }

}