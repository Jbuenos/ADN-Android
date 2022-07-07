package com.jomibusa.domain.payment.model

import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class CarPaymentTest {

    @Test
    fun carPayment_createCarPaymentWithCorrectInformation_successful() {

        //Arrange
        val register = CarRegister(Car(Plate("JRP310")), Date())

        //Act
        val carPayment = CarPayment(register)

        //Assert
        assertNotNull(carPayment)

    }

    @Test
    fun carPayment_calculateTotalServiceWithInformation_calculateZero() {

        //Arrange
        val register = CarRegister(Car(Plate("JRP310")), Date())
        val carPayment = CarPayment(register)

        //Act
        val totalService = carPayment.calculateTotalService()

        //Assert
        assertEquals(0.0, totalService, 0.0)

    }

    /*@Test
    fun carPayment_calculateTotalServiceWithInformation_calculate() {

        //Arrange
        *//*val register = CarRegister(
            Car(
                Plate("JRP310")
            ), Date(1657202138000)
        )*//*
        val register = CarRegister(
            Car(
                Plate("JRP310")
            ), Date(1657115738000)
        )
        val carPayment = CarPayment(register)

        //Act
        val totalService = carPayment.calculateTotalService()

        //Assert
        assertEquals(16000.0, totalService, 0.0)

    }*/


}