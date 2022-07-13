package com.jomibusa.domain.payment.service

import com.jomibusa.domain.register.exception.VehicleNotFoundInParkingException
import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.mockito.Mockito
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class MotorcyclePaymentServiceTest {

    @Test
    fun deleteMotorcycle_findNullRegister_exception() {
        //Arrange
        val register = CarRegister(Car(Plate("JRP251")), Date())
        val registerRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            Mockito.`when`(registerRepository.findRegisterByPlate(register.vehicle.plate))
                .thenReturn(null)
        }
        val paymentService = MotorcyclePaymentService(registerRepository)

        //Act
        //Assert
        assertThrows(VehicleNotFoundInParkingException::class.java) {
            runTest {
                paymentService.deleteRegister(register)
            }
        }
    }

    @Test
    fun deleteMotorcycle_findRegister_success() {
        //Arrange
        val register = CarRegister(Car(Plate("JRP251")), Date())
        val registerRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            Mockito.`when`(registerRepository.findRegisterByPlate(register.vehicle.plate))
                .thenReturn(register)
            Mockito.`when`(registerRepository.deleteRegister(register))
                .thenReturn(1)
        }
        val paymentService = MotorcyclePaymentService(registerRepository)

        //Act
        runTest {
            val result = paymentService.deleteRegister(register)

            //Assert
            assertEquals(1, result)
        }
    }

}