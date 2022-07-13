package com.jomibusa.adn_android.register.model

import com.jomibusa.domain.register.exception.ExistSameVehicleException
import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.model.MotorcycleRegister
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.register.service.CarRegisterService
import com.jomibusa.domain.register.service.MotorcycleRegisterService
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
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
    fun insertNewVehicleCarRegister_exception() {
        //Arrange
        val vehicle = Car(Plate("JRP310"))
        val registerRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            `when`(registerRepository.getAllRegisters()).thenReturn(listOf())
            `when`(registerRepository.findRegisterByPlate(vehicle.plate))
                .thenReturn(CarRegister(vehicle, Date()))
        }
        val motorcycleRegisterService = MotorcycleRegisterService(registerRepository)
        val carRegisterService = CarRegisterService(registerRepository)
        val registerProvider = RegisterProvider(carRegisterService, motorcycleRegisterService)

        //Act
        //Assert
        assertThrows(ExistSameVehicleException::class.java) {
            runTest {
                registerProvider.insertNewRegister(vehicle)
            }
        }
    }

    @Test
    fun insertNewVehicleMotorcycleRegister_exception() {
        //Arrange
        val vehicle = Motorcycle(450, Plate("UPA19C"))
        val registerRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            `when`(registerRepository.getAllRegisters()).thenReturn(listOf())
            `when`(registerRepository.findRegisterByPlate(vehicle.plate))
                .thenReturn(MotorcycleRegister(vehicle, Date()))
        }
        val motorcycleRegisterService = MotorcycleRegisterService(registerRepository)
        val carRegisterService = CarRegisterService(registerRepository)
        val registerProvider = RegisterProvider(carRegisterService, motorcycleRegisterService)

        //Act
        //Assert
        assertThrows(ExistSameVehicleException::class.java) {
            runTest {
                registerProvider.insertNewRegister(vehicle)
            }
        }
    }

}