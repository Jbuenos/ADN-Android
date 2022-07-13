package com.jomibusa.adn_android.payment.model

import com.jomibusa.domain.payment.service.CarPaymentService
import com.jomibusa.domain.payment.service.MotorcyclePaymentService
import com.jomibusa.domain.register.exception.VehicleNotFoundInParkingException
import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.register.service.CarRegisterService
import com.jomibusa.domain.register.service.MotorcycleRegisterService
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
        val paymentProvider = getPaymentProvider(registerRepository)

        //Act
        runTest {
            val result = paymentProvider.calculateService(VehicleType.CAR, vehicle.plate)

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
        val paymentProvider = getPaymentProvider(registerRepository)

        //Act
        runTest {
            val result = paymentProvider.calculateService(VehicleType.CAR, vehicle.plate)

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
        val paymentProvider = getPaymentProvider(registerRepository)

        //Act
        //Assert
        Assert.assertThrows(VehicleNotFoundInParkingException::class.java) {
            runTest {
                paymentProvider.payService(VehicleType.CAR, CarRegister(vehicle, Date()))
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

        val paymentProvider = getPaymentProvider(registerRepository)

        //Act
        runTest {
            val result = paymentProvider.payService(VehicleType.CAR, vehicleRegister)

            //Assert
            assertEquals(1, result)
        }
    }

    private fun getPaymentProvider(registerRepository: RegisterRepository): PaymentProvider {
        val motorcycleRegisterService = MotorcycleRegisterService(registerRepository)
        val carRegisterService = CarRegisterService(registerRepository)
        val carPaymentService = CarPaymentService(registerRepository)
        val motorcyclePaymentService = MotorcyclePaymentService(registerRepository)
        return PaymentProvider(
            carRegisterService,
            motorcycleRegisterService,
            carPaymentService,
            motorcyclePaymentService
        )
    }

}