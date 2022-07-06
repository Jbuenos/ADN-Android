package com.jomibusa.domain.payment.model

import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import org.junit.Assert
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
        Assert.assertNotNull(carPayment)

    }

    @Test
    fun carPayment_calculateTotalServiceWithInformation_calculate() {

        //Arrange
        val register = CarRegister(Car(Plate("JRP310")), Date(1656943200))
        val carPayment = CarPayment(register)

        //Act
        val totalService = carPayment.calculateTotalService()

        //Assert
        Assert.assertEquals(5000, totalService.toInt())

    }


}