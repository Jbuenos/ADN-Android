package com.jomibusa.infrastructure.register.repository

import com.jomibusa.domain.register.model.MotorcycleRegister
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class RoomMotorcycleParkingRegisterRepositoryTest {

    @Test
    fun getAllMotorcyclesAndRegisterFromParking_emptyList() {
        //Arrange
        val roomMotorcycleRepository =
            Mockito.mock(RoomMotorcycleParkingRegisterRepository::class.java)
        runTest {
            `when`(roomMotorcycleRepository.getAllRegisters()).thenReturn(listOf())
        }

        runTest {
            //Act
            val result = roomMotorcycleRepository.getAllRegisters()

            //Assert
            assertEquals(0, result.size)
        }
    }

    @Test
    fun getAllMotorcyclesAndRegisterFromParking_null() {
        //Arrange
        val roomMotorcycleRepository =
            Mockito.mock(RoomMotorcycleParkingRegisterRepository::class.java)
        runTest {
            `when`(roomMotorcycleRepository.getAllRegisters()).thenReturn(null)
        }

        runTest {
            //Act
            val result = roomMotorcycleRepository.getAllRegisters()

            //Assert
            assertNull(result)
        }
    }

    @Test
    fun getAllMotorcyclesAndRegisterFromParking_success() {
        //Arrange
        val listMotorcycleRegister = listOf(
            MotorcycleRegister(Motorcycle(250, Plate("UPA19C")), Date()),
            MotorcycleRegister(Motorcycle(150, Plate("UPA20C")), Date()),
            MotorcycleRegister(Motorcycle(180, Plate("UPA21C")), Date()),
            MotorcycleRegister(Motorcycle(450, Plate("UPA22C")), Date()),
            MotorcycleRegister(Motorcycle(230, Plate("UPA23C")), Date()),
        )
        val roomMotorcycleRepository =
            Mockito.mock(RoomMotorcycleParkingRegisterRepository::class.java)
        runTest {
            `when`(roomMotorcycleRepository.getAllRegisters()).thenReturn(listMotorcycleRegister)
        }

        runTest {
            //Act
            val result = roomMotorcycleRepository.getAllRegisters()

            //Assert
            assertEquals(5, result.size)
        }
    }

    @Test
    fun findMotorcycleAndRegisterByPlate_null() {
        //Arrange
        val plate = Plate("UPA19C")
        val roomMotorcycleRepository =
            Mockito.mock(RoomMotorcycleParkingRegisterRepository::class.java)
        runTest {
            `when`(roomMotorcycleRepository.findRegisterByPlate(plate)).thenReturn(null)
        }

        runTest {
            //Act
            val result = roomMotorcycleRepository.findRegisterByPlate(plate)

            //Assert
            assertNull(result)
        }
    }

    @Test
    fun findMotorcycleAndRegisterByPlate_success() {
        val plate = Plate("JRP304")
        val motorcycleRegister = MotorcycleRegister(Motorcycle(350, plate), Date())
        val roomMotorcycleRepository =
            Mockito.mock(RoomMotorcycleParkingRegisterRepository::class.java)
        runTest {
            `when`(roomMotorcycleRepository.findRegisterByPlate(plate)).thenReturn(
                motorcycleRegister
            )
        }

        runTest {
            //Act
            val result = roomMotorcycleRepository.findRegisterByPlate(plate)

            //Assert
            assertEquals(motorcycleRegister, result)
        }
    }

}