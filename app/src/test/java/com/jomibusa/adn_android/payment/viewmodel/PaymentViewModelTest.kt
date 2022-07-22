package com.jomibusa.adn_android.payment.viewmodel

import com.jomibusa.adn_android.payment.model.PaymentProvider
import com.jomibusa.adn_android.payment.model.VehicleType
import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class PaymentViewModelTest {

    private val paymentProvider = mock<PaymentProvider>()

    private lateinit var viewModel: PaymentViewModel

    @Before
    fun setUp() {
        viewModel = PaymentViewModel(provider = paymentProvider)
    }

    @Test
    fun calculateService_register_null() {
        //Arrange
        val vehicle = Car(Plate("HTM251"))
        runTest {
            whenever(paymentProvider.calculateService(VehicleType.CAR, vehicle.plate)).thenReturn(
                Pair(null, 0.0)
            )
        }
        //Act
        runTest {
            val result = paymentProvider.calculateService(VehicleType.CAR, vehicle.plate)

            //Assert
            assertNull(result.first)
        }
    }

    @Test
    fun calculateService_price_zero() {
        //Arrange
        val vehicle = Car(Plate("HTM251"))
        runTest {
            whenever(paymentProvider.calculateService(VehicleType.CAR, vehicle.plate)).thenReturn(
                Pair(null, 0.0)
            )
        }
        //Act
        runTest {
            val result = paymentProvider.calculateService(VehicleType.CAR, vehicle.plate)

            //Assert
            assertEquals(0.0, result.second)
        }
    }

    @Test
    fun calculateService_success() {
        //Arrange
        val vehicle = Car(Plate("HTM251"))
        val carRegister = CarRegister(vehicle, Date())
        runTest {
            whenever(paymentProvider.calculateService(VehicleType.CAR, vehicle.plate)).thenReturn(
                Pair(carRegister, 8000.0)
            )
        }
        //Act
        runTest {
            val result = paymentProvider.calculateService(VehicleType.CAR, vehicle.plate)

            //Assert
            assertEquals(carRegister, result.first)
        }
    }

    @Test
    fun doPayment_success() {
        //Arrange
        val vehicle = Car(Plate("HTM251"))
        val carRegister = CarRegister(vehicle, Date())
        runTest {
            whenever(paymentProvider.payService(VehicleType.CAR, carRegister)).thenReturn(1)
        }
        //Act
        runTest {
            val result = paymentProvider.payService(VehicleType.CAR, carRegister)

            //Assert
            assertEquals(1, result)
        }
    }

    @Test
    fun doPayment_not_success() {
        //Arrange
        val vehicle = Car(Plate("HTM251"))
        val carRegister = CarRegister(vehicle, Date())
        runTest {
            whenever(paymentProvider.payService(VehicleType.CAR, carRegister)).thenReturn(-1)
        }
        //Act
        runTest {
            val result = paymentProvider.payService(VehicleType.CAR, carRegister)

            //Assert
            assertEquals(-1, result)
        }
    }

}