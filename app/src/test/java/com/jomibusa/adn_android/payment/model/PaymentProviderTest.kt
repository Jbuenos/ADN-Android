package com.jomibusa.adn_android.payment.model

import com.jomibusa.domain.payment.service.PaymentService
import com.jomibusa.domain.register.exception.VehicleNotFoundInParkingException
import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.register.service.RegisterService
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class PaymentProviderTest {

    @Test
    fun calculatePaymentAndReturnNullRegister() {
        //Arrange
        val vehicle = Car(Plate("JRP310"))
        val registerRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            Mockito.`when`(registerRepository.findRegisterByPlate(vehicle.plate))
                .thenReturn(null)
        }
        val registerService = RegisterService(registerRepository)
        val paymentService = PaymentService(registerRepository)
        val paymentProvider = PaymentProvider(registerService, paymentService)

        //Act
        runTest {
            val result = paymentProvider.calculateService(vehicle.plate)

            //Assert
            assertNull(result.first)
        }
    }

    @Test
    fun calculatePaymentAndReturnServiceZeroByDate() {
        //Arrange
        val vehicle = Car(Plate("JRP310"))
        val registerRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            Mockito.`when`(registerRepository.findRegisterByPlate(vehicle.plate))
                .thenReturn(CarRegister(vehicle, Date()))
        }
        val registerService = RegisterService(registerRepository)
        val paymentService = PaymentService(registerRepository)
        val paymentProvider = PaymentProvider(registerService, paymentService)

        //Act
        runTest {
            val result = paymentProvider.calculateService(vehicle.plate)

            //Assert
            assertEquals(0.0, result.second)
        }
    }

    @Test
    fun payServiceAndGetResultDeleteRegister_exception() {
        //Arrange
        val vehicle = Car(Plate("JRP310"))
        val registerRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            Mockito.`when`(registerRepository.findRegisterByPlate(vehicle.plate))
                .thenReturn(null)
        }
        val registerService = RegisterService(registerRepository)
        val paymentService = PaymentService(registerRepository)
        val paymentProvider = PaymentProvider(registerService, paymentService)

        //Act
        //Assert
        Assert.assertThrows(VehicleNotFoundInParkingException::class.java) {
            runTest {
                paymentProvider.payService(CarRegister(vehicle, Date()))
            }
        }
    }

    @Test
    fun payServiceAndGetResultDeleteRegister_success() {
        //Arrange
        val vehicle = Car(Plate("JRP310"))
        val vehicleRegister = CarRegister(vehicle, Date())
        val registerRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            Mockito.`when`(registerRepository.findRegisterByPlate(vehicle.plate))
                .thenReturn(vehicleRegister)
            Mockito.`when`(registerRepository.deleteRegister(vehicleRegister))
                .thenReturn(1)
        }
        val registerService = RegisterService(registerRepository)
        val paymentService = PaymentService(registerRepository)
        val paymentProvider = PaymentProvider(registerService, paymentService)

        //Act
        runTest {
            val result = paymentProvider.payService(vehicleRegister)

            //Assert
            assertEquals(1, result)
        }
    }

}