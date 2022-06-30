package com.jomibusa.domain.vehicle.service

import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.domain.vehicle.repository.CarRepository
import com.jomibusa.domain.vehicle.repository.MotorcycleRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class VehicleServiceTest {

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
    fun getAllMotorcycles_emptyList_success() = runTest {
        //Arrange
        val motorcycleRepository = Mockito.mock(MotorcycleRepository::class.java)
        `when`(motorcycleRepository.getAllVehicles()).thenReturn(listOf())
        val motorcycleService = VehicleService(motorcycleRepository)

        //Act
        val vehicleList = motorcycleService.getAllVehicles()

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
    fun getAllMotorcycles_nullResult_null() = runTest {
        //Arrange
        val motorcycleRepository = Mockito.mock(MotorcycleRepository::class.java)
        `when`(motorcycleRepository.getAllVehicles()).thenReturn(null)
        val motorcycleService = VehicleService(motorcycleRepository)

        //Act
        val vehicleList = motorcycleService.getAllVehicles()

        //Assert
        assertNull(vehicleList)
    }

    @Test
    fun getCarByPlate_existInParking_success() = runTest {
        //Arrange
        val carList = listOf(Car(Plate("JRP251")), Car(Plate("BTI371")), Car(Plate("WYZ444")))
        val carRepository = Mockito.mock(CarRepository::class.java)
        `when`(carRepository.getAllVehicles()).thenReturn(carList)
        val carService = VehicleService(carRepository)

        //Act
        val vehicle = carService.getVehicleByPlate(Plate("JRP251"))

        //Assert
        assertEquals("JRP251", vehicle?.plate?.numPlate)
    }

    @Test
    fun getMotorcycleByPlate_existInParking_success() = runTest {
        //Arrange
        val motorcycleList =
            listOf(Motorcycle(400, Plate("UPA19C")), Car(Plate("HTM21J")), Car(Plate("JKL4MM")))
        val motorcycleRepository = Mockito.mock(MotorcycleRepository::class.java)
        `when`(motorcycleRepository.getAllVehicles()).thenReturn(motorcycleList)
        val motorcycleService = VehicleService(motorcycleRepository)

        //Act
        val vehicle = motorcycleService.getVehicleByPlate(Plate("JRP251"))

        //Assert
        assertEquals("UPA19C", vehicle?.plate?.numPlate)
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

    @Test
    fun getMotorcycleByPlate_existInParking_null() = runTest {
        //Arrange
        val motorcycleRepository = Mockito.mock(CarRepository::class.java)
        `when`(motorcycleRepository.findVehicleByPlate(Plate("UPA19B"))).thenReturn(null)
        val motorcycleService = VehicleService(motorcycleRepository)

        //Act
        val vehicle = motorcycleService.getVehicleByPlate(Plate("JRP251"))

        //Assert
        assertNull(vehicle)
    }

}