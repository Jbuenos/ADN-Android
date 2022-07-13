package com.jomibusa.domain.register.service

import com.jomibusa.domain.register.exception.CapacityParkingExceededException
import com.jomibusa.domain.register.exception.ExistSameVehicleException
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
class CarRegisterServiceTest {

    @Test
    fun register_validateMaxSpacesCar_exception() {

        //Arrange
        val listMotorcycles = listOf(
            CarRegister(Car(Plate("JRP251")), Date()),
            CarRegister(Car(Plate("JRP252")), Date()),
            CarRegister(Car(Plate("JRP253")), Date()),
            CarRegister(Car(Plate("JRP254")), Date()),
            CarRegister(Car(Plate("JRP255")), Date()),
            CarRegister(Car(Plate("JRP256")), Date()),
            CarRegister(Car(Plate("JRP257")), Date()),
            CarRegister(Car(Plate("JRP258")), Date()),
            CarRegister(Car(Plate("JRP259")), Date()),
            CarRegister(Car(Plate("JRP260")), Date()),
            CarRegister(Car(Plate("JRP261")), Date()),
            CarRegister(Car(Plate("JRP262")), Date()),
            CarRegister(Car(Plate("JRP263")), Date()),
            CarRegister(Car(Plate("JRP264")), Date()),
            CarRegister(Car(Plate("JRP265")), Date()),
            CarRegister(Car(Plate("JRP266")), Date()),
            CarRegister(Car(Plate("JRP267")), Date()),
            CarRegister(Car(Plate("JRP268")), Date()),
            CarRegister(Car(Plate("JRP269")), Date()),
            CarRegister(Car(Plate("JRP270")), Date())
        )

        val register = CarRegister(Car(Plate("JRP310")), Date())
        val registerServiceRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            Mockito.`when`(registerServiceRepository.getAllRegisters()).thenReturn(listMotorcycles)
        }
        val registerService = CarRegisterService(registerServiceRepository)

        //Act
        //Assert
        Assert.assertThrows(CapacityParkingExceededException::class.java) {
            runTest {
                registerService.insertNewRegister(register)
            }
        }
    }

    @Test
    fun register_findPreviousRegisterCar_exception() {

        //Arrange
        val register = CarRegister(Car(Plate("JRP310")), Date())
        val registerServiceRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            Mockito.`when`(registerServiceRepository.getAllRegisters()).thenReturn(listOf())
            Mockito.`when`(registerServiceRepository.findRegisterByPlate(register.vehicle.plate))
                .thenReturn(register)
        }
        val registerService = CarRegisterService(registerServiceRepository)

        //Act
        //Assert
        Assert.assertThrows(ExistSameVehicleException::class.java) {
            runTest {
                registerService.insertNewRegister(register)
            }
        }
    }
}