package com.jomibusa.adn_android.register.model

import com.jomibusa.domain.register.exception.ExistSameVehicleException
import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.register.service.RegisterService
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class RegisterProviderTest {

    @Test
    fun insertNewVehicleRegister_exception() {
        //Arrange
        val vehicle = Car(Plate("JRP310"))
        val registerRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            `when`(registerRepository.getAllRegisters()).thenReturn(listOf())
            `when`(registerRepository.findRegisterByPlate(vehicle.plate))
                .thenReturn(CarRegister(vehicle, Date()))
        }
        val registerService = RegisterService(registerRepository)
        val registerProvider = RegisterProvider(registerService)

        //Act
        //Assert
        assertThrows(ExistSameVehicleException::class.java) {
            runTest {
                registerProvider.insertNewRegister(vehicle)
            }
        }
    }

}