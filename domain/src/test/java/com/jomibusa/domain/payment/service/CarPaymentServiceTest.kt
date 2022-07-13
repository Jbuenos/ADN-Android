package com.jomibusa.domain.payment.service

import com.jomibusa.domain.register.exception.VehicleNotFoundInParkingException
import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class CarPaymentServiceTest {

    @Test
    fun deleteCar_findNullRegister_exception() {
        //Arrange
        val register = CarRegister(Car(Plate("JRP251")), Date())
        val registerRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            Mockito.`when`(registerRepository.findRegisterByPlate(register.vehicle.plate))
                .thenReturn(null)
        }
        val paymentService = CarPaymentService(registerRepository)

        //Act
        //Assert
        Assert.assertThrows(VehicleNotFoundInParkingException::class.java) {
            runTest {
                paymentService.deleteRegister(register)
            }
        }
    }

    @Test
    fun deleteCar_findRegister_success() {
        //Arrange
        val register = CarRegister(Car(Plate("JRP251")), Date())
        val registerRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            Mockito.`when`(registerRepository.findRegisterByPlate(register.vehicle.plate))
                .thenReturn(register)
            Mockito.`when`(registerRepository.deleteRegister(register))
                .thenReturn(1)
        }
        val paymentService = CarPaymentService(registerRepository)

        //Act
        runTest {
            val result = paymentService.deleteRegister(register)

            //Assert
            Assert.assertEquals(1, result)
        }
    }

}