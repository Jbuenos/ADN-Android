package com.jomibusa.domain.vehicle.service

import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.domain.vehicle.repository.MotorcycleRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class MotorcycleServiceTest {

    @Test
    fun getAllMotorcycles_emptyList_success() = runTest {
        //Arrange
        val motorcycleRepository = Mockito.mock(MotorcycleRepository::class.java)
        Mockito.`when`(motorcycleRepository.getAllVehicles()).thenReturn(listOf())
        val motorcycleService = VehicleService(motorcycleRepository)

        //Act
        val vehicleList = motorcycleService.getAllVehicles()

        //Assert
        Assert.assertEquals(0, vehicleList?.size)
    }

    @Test
    fun getAllMotorcycles_nullResult_null() = runTest {
        //Arrange
        val motorcycleRepository = Mockito.mock(MotorcycleRepository::class.java)
        Mockito.`when`(motorcycleRepository.getAllVehicles()).thenReturn(null)
        val motorcycleService = VehicleService(motorcycleRepository)

        //Act
        val vehicleList = motorcycleService.getAllVehicles()

        //Assert
        assertNull(vehicleList)
    }

    @Test
    fun getMotorcycleByPlate_existInParking_success() = runTest {
        //Arrange
        val motorcycleList =
            listOf(
                Motorcycle(400, Plate("UPA19C")),
                Motorcycle(250, Plate("HTM21J")),
                Motorcycle(500, Plate("JKL4MM"))
            )
        val motorcycleRepository = Mockito.mock(MotorcycleRepository::class.java)
        `when`(motorcycleRepository.getAllVehicles()).thenReturn(motorcycleList)
        val motorcycleService = VehicleService(motorcycleRepository)

        //Act
        val vehicle = motorcycleService.getVehicleByPlate(Plate("JRP251"))

        //Assert
        assertEquals("UPA19C", vehicle?.plate?.numPlate)
    }

    @Test
    fun getMotorcycleByPlate_existInParking_null() = runTest {
        //Arrange
        val motorcycleRepository = Mockito.mock(MotorcycleRepository::class.java)
        Mockito.`when`(motorcycleRepository.findVehicleByPlate(Plate("UPA19B"))).thenReturn(null)
        val motorcycleService = VehicleService(motorcycleRepository)

        //Act
        val vehicle = motorcycleService.getVehicleByPlate(Plate("JRP251"))

        //Assert
        assertNull(vehicle)
    }

}