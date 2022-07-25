package com.jomibusa.infrastructure.vehicle.repository

import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class RoomCarRepositoryTest {

    @Test
    fun getAllVehicle_emptyList() {
        //Arrange
        val roomCarRepository = mock(RoomCarRepository::class.java)
        runTest {
            `when`(roomCarRepository.getAllVehicles()).thenReturn(listOf())
        }

        runTest {
            //Act
            val result = roomCarRepository.getAllVehicles()

            //Assert
            assertEquals(0, result.size)
        }
    }

    @Test
    fun getAllVehicle_success() {
        //Arrange
        val listCars = listOf(
            Car(Plate("JRP304")), Car(Plate("BTI371")), Car(
                Plate("WYZ444")
            ), Car(Plate("HMT251"))
        )
        val roomCarRepository = mock(RoomCarRepository::class.java)
        runTest {
            `when`(roomCarRepository.getAllVehicles()).thenReturn(listCars)
        }

        runTest {
            //Act
            val result = roomCarRepository.getAllVehicles()

            //Assert
            assertEquals(4, result.size)
        }
    }

    @Test
    fun getAllVehicle_list_null() {
        //Arrange
        val roomCarRepository = mock(RoomCarRepository::class.java)
        runTest {
            `when`(roomCarRepository.getAllVehicles()).thenReturn(null)
        }

        runTest {
            //Act
            val result = roomCarRepository.getAllVehicles()

            //Assert
            assertNull(result)
        }
    }

    @Test
    fun findVehicleByPlate_null() {
        //Arrange
        val plate = Plate("JRP304")
        val roomCarRepository = mock(RoomCarRepository::class.java)
        runTest {
            `when`(roomCarRepository.findVehicleByPlate(plate)).thenReturn(null)
        }

        runTest {
            //Act
            val result = roomCarRepository.findVehicleByPlate(plate)

            //Assert
            assertNull(result)
        }
    }

    @Test
    fun findVehicleByPlate_success() {
        //Arrange
        val plate = Plate("JRP304")
        val car = Car(plate)
        val roomCarRepository = mock(RoomCarRepository::class.java)
        runTest {
            `when`(roomCarRepository.findVehicleByPlate(plate)).thenReturn(car)
        }

        runTest {
            //Act
            val result = roomCarRepository.findVehicleByPlate(plate)

            //Assert
            assertEquals(car, result)
        }
    }

}