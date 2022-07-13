package com.jomibusa.domain.register.service

import com.jomibusa.domain.register.exception.CapacityParkingExceededException
import com.jomibusa.domain.register.exception.ExistSameVehicleException
import com.jomibusa.domain.register.model.MotorcycleRegister
import com.jomibusa.domain.register.repository.RegisterRepository
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
class MotorcycleRegisterServiceTest {

    @Test
    fun register_validateMaxSpacesMotorcycle_exception() {

        //Arrange
        val listMotorcycles = listOf(
            MotorcycleRegister(Motorcycle(450, Plate("JRP251")), Date()),
            MotorcycleRegister(Motorcycle(450, Plate("JRP252")), Date()),
            MotorcycleRegister(Motorcycle(450, Plate("JRP253")), Date()),
            MotorcycleRegister(Motorcycle(450, Plate("JRP254")), Date()),
            MotorcycleRegister(Motorcycle(450, Plate("JRP255")), Date()),
            MotorcycleRegister(Motorcycle(450, Plate("JRP256")), Date()),
            MotorcycleRegister(Motorcycle(450, Plate("JRP257")), Date()),
            MotorcycleRegister(Motorcycle(450, Plate("JRP258")), Date()),
            MotorcycleRegister(Motorcycle(450, Plate("JRP259")), Date()),
            MotorcycleRegister(Motorcycle(450, Plate("JRP260")), Date())
        )

        val register = MotorcycleRegister(Motorcycle(450, Plate("JRP310")), Date())
        val registerServiceRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            `when`(registerServiceRepository.getAllRegisters()).thenReturn(listMotorcycles)
        }
        val registerService = MotorcycleRegisterService(registerServiceRepository)

        //Act
        //Assert
        assertThrows(CapacityParkingExceededException::class.java) {
            runTest {
                registerService.insertNewRegister(register)
            }
        }
    }

    @Test
    fun register_findPreviousRegisterMotorcycle_exception() {

        //Arrange
        val register = MotorcycleRegister(Motorcycle(450, Plate("JRP310")), Date())
        val registerServiceRepository = Mockito.mock(RegisterRepository::class.java)
        runTest {
            `when`(registerServiceRepository.getAllRegisters()).thenReturn(listOf())
            `when`(registerServiceRepository.findRegisterByPlate(register.vehicle.plate))
                .thenReturn(register)
        }
        val registerService = MotorcycleRegisterService(registerServiceRepository)

        //Act
        //Assert
        assertThrows(ExistSameVehicleException::class.java) {
            runTest {
                registerService.insertNewRegister(register)
            }
        }
    }

}