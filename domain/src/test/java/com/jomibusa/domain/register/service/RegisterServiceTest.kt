package com.jomibusa.domain.register.service

import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.model.MotorcycleRegister
import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class RegisterServiceTest {

    @Test
    fun register_validateMaxSpacesCar_exception() = runTest {

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

        val register = CarRegister(Car(Plate("JRP310")), Date())
        val registerServiceRepository = Mockito.mock(RegisterRepository::class.java)
        Mockito.`when`(registerServiceRepository.getAllRegisters()).thenReturn(listMotorcycles)
        val registerService = RegisterService(registerServiceRepository)

        //Act
        //Assert
        /*Assert.assertThrows(CapacityParkingExceededException::class.java) {
            kotlinx.coroutines.test.runTest {
                registerService.insertNewRegister(register)
            }
        }*/
    }

}