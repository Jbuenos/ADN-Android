package com.jomibusa.domain.vehicle.service

import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.domain.vehicle.repository.CarRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class CarServiceTest {

    @Test
    fun getAllCars_emptyList_success() = runTest {
        //Arrange
        val carRepository = Mockito.mock(CarRepository::class.java)
        `when`(carRepository.getAllVehicles()).thenReturn(listOf())
        val carService = VehicleService(carRepository)

        //Act
        val vehicleList = carService.getAllVehicles()

        //Assert
        assertEquals(0, vehicleList?.size)
    }

    @Test
    fun getAllCars_nullResult_null() = runTest {
        //Arrange
        val carRepository = Mockito.mock(CarRepository::class.java)
        `when`(carRepository.getAllVehicles()).thenReturn(null)
        val carService = VehicleService(carRepository)

        //Act
        val vehicleList = carService.getAllVehicles()

        //Assert
        assertNull(vehicleList)
    }

    @Test
    fun getCarByPlate_existInParking_success() = runTest {
        /*//Arrange
        val carList = listOf(Car(Plate("JRP251")), Car(Plate("BTI371")), Car(Plate("WYZ444")))
        val carRepository = Mockito.mock(CarRepository::class.java)
        `when`(carRepository.getAllVehicles()).thenReturn(carList)
        val carService = VehicleService(carRepository)

        //Act
        val vehicle = carService.getVehicleByPlate(Plate("JRP251"))

        //Assert
        assertEquals("JRP251", vehicle?.plate?.numPlate)*/
    }

    @Test
    fun getCarByPlate_noExistInParking_null() = runTest {
        //Arrange
        val carRepository = Mockito.mock(CarRepository::class.java)
        `when`(carRepository.findVehicleByPlate(Plate("JRP255"))).thenReturn(null)
        val carService = VehicleService(carRepository)

        //Act
        val vehicle = carService.getVehicleByPlate(Plate("JRP251"))

        //Assert
        assertNull(vehicle)
    }

}