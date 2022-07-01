package com.jomibusa.domain.register.service

import com.jomibusa.domain.register.exception.CapacityParkingExceededException
import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.domain.vehicle.service.VehicleService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class MotorcycleRegisterServiceTest {

    @Test
    fun register_validateMaxSpacesCar_exception() = runTest {

        /*//Arrange
        val expectedMessage = "Capacidad máxima del parqueadero alcanzada"
        val register = CarRegister(Car(Plate("JRP310")), Date())
        val registerServiceRepository = Mockito.mock(RegisterRepository::class.java)
        Mockito.`when`(registerServiceRepository.insertRegister(register)).thenReturn(null)
        val registerService = RegisterService(registerServiceRepository)

        //Act
        try {
            registerService.insertNewRegister(register)
            Assert.fail()
        } catch (ex: Exception) {
            //Assert
            assertEquals(expectedMessage, ex.message)
        }*/
    }

}