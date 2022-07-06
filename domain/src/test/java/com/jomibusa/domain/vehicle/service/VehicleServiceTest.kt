package com.jomibusa.domain.vehicle.service

import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.domain.vehicle.repository.VehicleRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class VehicleServiceTest {

    @Test
    fun getAllVehicles_emptyList_success() = runTest {
        //Arrange
        val vehicleRepository = Mockito.mock(VehicleRepository::class.java)
        `when`(vehicleRepository.getAllVehicles()).thenReturn(listOf())
        val vehicleService = VehicleService(vehicleRepository)

        //Act
        val vehicleList = vehicleService.getAllVehicles()

        //Assert
        assertEquals(0, vehicleList.size)
    }

    @Test
    fun getAllVehicles_nullResult_null() = runTest {
        //Arrange
        val vehicleRepository = Mockito.mock(VehicleRepository::class.java)
        `when`(vehicleRepository.getAllVehicles()).thenReturn(null)
        val vehicleService = VehicleService(vehicleRepository)

        //Act
        val vehicleList = vehicleService.getAllVehicles()

        //Assert
        assertNull(vehicleList)
    }

    @Test
    fun getVehicleByPlate_existInParking_success() = runTest {
        //Arrange
        val vehicleToFind = Car(Plate("JRP251"))
        val vehicleRepository = Mockito.mock(VehicleRepository::class.java)
        `when`(vehicleRepository.findVehicleByPlate(Plate("JRP251"))).thenReturn(vehicleToFind)
        val vehicleService = VehicleService(vehicleRepository)

        //Act
        val vehicle = vehicleService.getVehicleByPlate(Plate("JRP251"))

        //Assert
        assertEquals("JRP251", vehicle?.plate?.numPlate)
    }

    @Test
    fun getAllVehicles_success() = runTest {
        //Arrange
        val vehicleList = listOf(Car(Plate("JRP251")), Car(Plate("BTI371")), Car(Plate("WYZ444")))
        val vehicleRepository = Mockito.mock(VehicleRepository::class.java)
        `when`(vehicleRepository.getAllVehicles()).thenReturn(vehicleList)
        val vehicleService = VehicleService(vehicleRepository)

        //Act
        val vehicles = vehicleService.getAllVehicles()

        //Assert
        assertEquals(3, vehicles.size)
    }

    @Test
    fun getVehicleByPlate_noExistInParking_null() = runTest {
        //Arrange
        val vehicleRepository = Mockito.mock(VehicleRepository::class.java)
        val vehicleModel = Car(Plate("JRP255"))
        `when`(vehicleRepository.findVehicleByPlate(vehicleModel.numPlate)).thenReturn(null)
        val vehicleService = VehicleService(vehicleRepository)

        //Act
        val vehicle = vehicleService.getVehicleByPlate(vehicleModel.plate)

        //Assert
        Assert.assertNull(vehicle)
    }

}