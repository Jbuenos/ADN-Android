package com.jomibusa.domain.payment.model

import com.jomibusa.domain.register.model.MotorcycleRegister
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class MotorcyclePaymentTest {

    @Test
    fun motorcyclePayment_createMotorcyclePaymentWithCorrectInformation_successful() {

        //Arrange
        val register = MotorcycleRegister(Motorcycle(250, Plate("UPA19C")), Date())

        //Act
        val motorcyclePayment = MotorcyclePayment(register)

        //Assert
        assertNotNull(motorcyclePayment)

    }

    @Test
    fun motorcyclePayment_calculateTotalServiceWithInformation_calculateZero() {

        //Arrange
        val register = MotorcycleRegister(Motorcycle(400, Plate("UPA19C")), Date())
        val motorcyclePayment = CarPayment(register)

        //Act
        val totalService = motorcyclePayment.calculateTotalService()

        //Assert
        assertEquals(0.0, totalService, 0.0)

    }

}