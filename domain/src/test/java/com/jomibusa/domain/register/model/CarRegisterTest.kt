package com.jomibusa.domain.register.model

import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDate
import java.util.*

class CarRegisterTest {

    @Test
    fun car_createCarRegisterWithCorrectInformation_successful() {

        //Arrange
        val plate = Plate("JRP310")

        //Act
        val carRegister = CarRegister(Car(plate), Date())

        //Assert
        assertNotNull(carRegister)

    }

    @Test
    fun car_createCarRegisterWithPlateWithA_exception() {

        //Arrange
        val plate = Plate("ABS190")
        //var date = LocalDate.of(2018, 12, 31)
        val expectedMessage = "Restricción para placas que comienza con A y es lunes o domingo"

        //Act
        try {
            val carRegister = CarRegister(Car(plate), Date())
            Assert.fail()
        } catch (ex: Exception) {
            //Assert
            assertEquals(expectedMessage, ex.message)
        }
    }

}