package com.jomibusa.infrastructure.register.repository

import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.vehicle.model.Car
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
class RoomCarParkingRegisterRepositoryTest {

    @Test
    fun getAllCarsAndRegisterFromParking_emptyList() {
        //Arrange
        val roomCarRepository = Mockito.mock(RoomCarParkingRegisterRepository::class.java)
        runTest {
            `when`(roomCarRepository.getAllRegisters()).thenReturn(listOf())
        }

        runTest {
            //Act
            val result = roomCarRepository.getAllRegisters()

            //Assert
            assertEquals(0, result.size)
        }
    }

    @Test
    fun getAllCarsAndRegisterFromParking_null() {
        //Arrange
        val roomCarRepository = Mockito.mock(RoomCarParkingRegisterRepository::class.java)
        runTest {
            `when`(roomCarRepository.getAllRegisters()).thenReturn(null)
        }

        runTest {
            //Act
            val result = roomCarRepository.getAllRegisters()

            //Assert
            assertNull(result)
        }
    }

    @Test
    fun getAllCarsAndRegisterFromParking_success() {
        //Arrange
        val listCarRegister = listOf(
            CarRegister(Car(Plate("JRP304")), Date()),
            CarRegister(Car(Plate("JRP305")), Date()),
            CarRegister(Car(Plate("JRP306")), Date()),
            CarRegister(Car(Plate("JRP307")), Date()),
            CarRegister(Car(Plate("JRP308")), Date())
        )
        val roomCarRepository = Mockito.mock(RoomCarParkingRegisterRepository::class.java)
        runTest {
            `when`(roomCarRepository.getAllRegisters()).thenReturn(listCarRegister)
        }

        runTest {
            //Act
            val result = roomCarRepository.getAllRegisters()

            //Assert
            assertEquals(5, result.size)
        }
    }

    @Test
    fun findCarAndRegisterByPlate_null() {
        //Arrange
        val plate = Plate("JRP304")
        val roomCarRepository = Mockito.mock(RoomCarParkingRegisterRepository::class.java)
        runTest {
            `when`(roomCarRepository.findRegisterByPlate(plate)).thenReturn(null)
        }

        runTest {
            //Act
            val result = roomCarRepository.findRegisterByPlate(plate)

            //Assert
            assertNull(result)
        }
    }

    @Test
    fun findCarAndRegisterByPlate_success() {
        val plate = Plate("JRP304")
        val carRegister = CarRegister(Car(plate), Date())
        val roomCarRepository = Mockito.mock(RoomCarParkingRegisterRepository::class.java)
        runTest {
            `when`(roomCarRepository.findRegisterByPlate(plate)).thenReturn(carRegister)
        }

        runTest {
            //Act
            val result = roomCarRepository.findRegisterByPlate(plate)

            //Assert
            assertEquals(carRegister, result)
        }
    }

}