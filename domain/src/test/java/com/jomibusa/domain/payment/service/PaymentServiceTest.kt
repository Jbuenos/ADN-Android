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
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class PaymentServiceTest {

    @Test
    fun deleteVehicle_findNullRegister_exception() {
        //Arrange
        val register = CarRegister(Car(Plate("JRP251")), Date())
        val registerRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            Mockito.`when`(registerRepository.findRegisterByPlate(Plate(anyString())))
                .thenReturn(null)
        }
        val paymentService = PaymentService(registerRepository)

        //Act
        //Assert
        Assert.assertThrows(VehicleNotFoundInParkingException::class.java) {
            runTest {
                paymentService.deleteRegister(register)
            }
        }
    }

}