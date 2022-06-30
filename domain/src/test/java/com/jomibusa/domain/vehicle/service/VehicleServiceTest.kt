package com.jomibusa.domain.vehicle.service

import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.domain.vehicle.repository.CarRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class VehicleServiceTest {

    @Test
    fun getAllVehicles_emptyList_success() = runTest {
        //Arrange
        val carRepository = Mockito.mock(CarRepository::class.java)
        `when`(carRepository.getAllVehicles()).thenReturn(listOf())
        val carService = VehicleService(carRepository)

        //Act
        val vehicleList = carService.getAllVehicles()

        //Assert
        if (vehicleList != null) {
            assertEquals(0, vehicleList.size)
        }
    }

}