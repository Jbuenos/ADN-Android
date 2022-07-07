package com.jomibusa.domain.payment.model

import com.jomibusa.domain.register.model.MotorcycleRegister
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.util.*

class MotorcyclePaymentTest {

    @Test
    fun motorcyclePayment_createMotorcyclePaymentWithCorrectInformation_successful() {

        //Arrange
        val register = MotorcycleRegister(Motorcycle(250, Plate("JRP310")), Date())

        //Act
        val motorcyclePayment = MotorcyclePayment(register)

        //Assert
        assertNotNull(motorcyclePayment)

    }

    @Test
    fun motorcyclePayment_calculateTotalServiceWithInformation_calculate() {

        /* //Arrange
         val register = MotorcycleRegister(Motorcycle(250, Plate("JRP310")), Date(1656943200))
         val motorcyclePayment = MotorcyclePayment(register)

         //Act
         val totalService = motorcyclePayment.calculateTotalService()

         //Assert
         Assert.assertEquals(5000, totalService.toInt())*/

    }

}