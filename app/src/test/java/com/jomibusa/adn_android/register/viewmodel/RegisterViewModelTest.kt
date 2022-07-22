package com.jomibusa.adn_android.register.viewmodel

import com.jomibusa.adn_android.register.model.RegisterProvider
import com.jomibusa.domain.register.exception.CapacityParkingExceededException
import com.jomibusa.domain.register.exception.ExistSameVehicleException
import com.jomibusa.domain.vehicle.exception.InvalidPatternPlateException
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class RegisterViewModelTest {

    private val registerProvider = mock<RegisterProvider>()

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        viewModel = RegisterViewModel(provider = registerProvider)
    }

    @Test
    fun insertNewRegister_capacityParkingExceed_exception() {
        //Arrange
        val vehicle = Car(Plate("HTM251"))
        runTest {
            whenever(registerProvider.insertNewRegister(vehicle)).thenThrow(
                CapacityParkingExceededException()
            )
        }
        //Act
        //Assert
        Assert.assertThrows(CapacityParkingExceededException::class.java) {
            runTest {
                registerProvider.insertNewRegister(vehicle)
            }
        }
    }

    @Test
    fun insertNewRegister_existSameVehicle_exception() {
        //Arrange
        val vehicle = Car(Plate("HTM251"))
        runTest {
            whenever(registerProvider.insertNewRegister(vehicle)).thenThrow(
                ExistSameVehicleException()
            )
        }
        //Act
        //Assert
        Assert.assertThrows(ExistSameVehicleException::class.java) {
            runTest {
                registerProvider.insertNewRegister(vehicle)
            }
        }
    }

    @Test
    fun insertNewRegister_invalidPatternPlate_exception() {
        //Arrange
        val vehicle = Car(Plate("HTM251"))
        runTest {
            whenever(registerProvider.insertNewRegister(vehicle)).thenThrow(
                InvalidPatternPlateException()
            )
        }
        //Act
        //Assert
        Assert.assertThrows(InvalidPatternPlateException::class.java) {
            runTest {
                registerProvider.insertNewRegister(vehicle)
            }
        }
    }

}