package com.jomibusa.infrastructure.vehicle.repository

import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class RoomMotorcycleRepositoryTest {

    @Test
    fun getAllVehicle_emptyList() {
        //Arrange
        val roomMotorcycleRepository = mock(RoomMotorcycleRepository::class.java)
        runTest {
            `when`(roomMotorcycleRepository.getAllVehicles()).thenReturn(listOf())
        }

        runTest {
            //Act
            val result = roomMotorcycleRepository.getAllVehicles()

            //Assert
            assertEquals(0, result.size)
        }
    }

    @Test
    fun getAllVehicle_success() {
        //Arrange
        val listMotorcycles = listOf(
            Motorcycle(250, Plate("UPA19C")),
            Motorcycle(180, Plate("OBO99B")),
            Motorcycle(300, Plate("KWY60N")),
            Motorcycle(450, Plate("KKK12T"))
        )
        val roomMotorcycleRepository = mock(RoomMotorcycleRepository::class.java)
        runTest {
            `when`(roomMotorcycleRepository.getAllVehicles()).thenReturn(listMotorcycles)
        }

        runTest {
            //Act
            val result = roomMotorcycleRepository.getAllVehicles()

            //Assert
            assertEquals(4, result.size)
        }
    }

    @Test
    fun getAllVehicle_list_null() {
        //Arrange
        val roomMotorcycleRepository = mock(RoomMotorcycleRepository::class.java)
        runTest {
            `when`(roomMotorcycleRepository.getAllVehicles()).thenReturn(null)
        }

        runTest {
            //Act
            val result = roomMotorcycleRepository.getAllVehicles()

            //Assert
            assertNull(result)
        }
    }

    @Test
    fun findVehicleByPlate_null() {
        //Arrange
        val plate = Plate("UPA19C")
        val roomMotorcycleRepository = mock(RoomMotorcycleRepository::class.java)
        runTest {
            `when`(roomMotorcycleRepository.findVehicleByPlate(plate)).thenReturn(null)
        }

        runTest {
            //Act
            val result = roomMotorcycleRepository.findVehicleByPlate(plate)

            //Assert
            assertNull(result)
        }
    }

    @Test
    fun findVehicleByPlate_success() {
        //Arrange
        val plate = Plate("UPA19C")
        val motorcycle = Motorcycle(250, plate)
        val roomMotorcycleRepository = mock(RoomMotorcycleRepository::class.java)
        runTest {
            `when`(roomMotorcycleRepository.findVehicleByPlate(plate)).thenReturn(motorcycle)
        }

        runTest {
            //Act
            val result = roomMotorcycleRepository.findVehicleByPlate(plate)

            //Assert
            assertEquals(motorcycle, result)
        }
    }

}