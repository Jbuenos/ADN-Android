package com.jomibusa.domain.vehicle.service

import com.jomibusa.domain.register.model.MotorcycleRegister
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
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class MotorcycleServiceTest {

    @Test
    fun getAllMotorcycles_emptyList_success() = runTest {
        //Arrange
        val motorcycleRepository = Mockito.mock(MotorcycleRepository::class.java)
        `when`(motorcycleRepository.getAllMotorcycles()).thenReturn(listOf())
        val motorcycleService = VehicleService(motorcycleRepository)

        //Act
        val vehicleList = motorcycleService.getAllMotorcycles()

        //Assert
        assertEquals(0, vehicleList.size)
    }

    @Test
    fun getAllMotorcycles_nullResult_null() = runTest {
        //Arrange
        val motorcycleRepository = Mockito.mock(MotorcycleRepository::class.java)
        `when`(motorcycleRepository.getAllMotorcycles()).thenReturn(null)
        val motorcycleService = VehicleService(motorcycleRepository)

        //Act
        val vehicleList = motorcycleService.getAllMotorcycles()

        //Assert
        assertNull(vehicleList)
    }

    @Test
    fun getMotorcycleByPlate_existInParking_success() = runTest {
        /*//Arrange
        val motorcycleList =
            listOf(
                Motorcycle(400, Plate("UPA19C")),
                Motorcycle(250, Plate("HTM21J")),
                Motorcycle(500, Plate("JKL45M"))
            )
        val register = MotorcycleRegister(Motorcycle(400, Plate("UPA19C")), Date())
        val motorcycleRepository = Mockito.mock(MotorcycleRepository::class.java)
        `when`(motorcycleRepository.findVehicleByPlate(register)).thenReturn(Motorcycle(400, Plate("UPA19C")))
        `when`(motorcycleRepository.getAllMotorcycles()).thenReturn(motorcycleList)
        val motorcycleService = VehicleService(motorcycleRepository)

        //Act
        val vehicle = motorcycleService.getVehicleByPlate(
            MotorcycleRegister(Motorcycle(400, Plate("UPA19C")), Date())
        )

        //Assert
        assertEquals(
            "UPA19C", vehicle?.plate?.numPlate
        )*/
    }

    @Test
    fun getMotorcycleByPlate_existInParking_null() = runTest {
        //Arrange
        val motorcycleRepository = Mockito.mock(MotorcycleRepository::class.java)
        `when`(
            motorcycleRepository.findVehicleByPlate(
                MotorcycleRegister(
                    Motorcycle(
                        400,
                        Plate("UPA19B")
                    ), Date()
                )
            )
        ).thenReturn(null)
        val motorcycleService = VehicleService(motorcycleRepository)

        //Act
        val vehicle = motorcycleService.getVehicleByPlate(
            MotorcycleRegister(
                Motorcycle(400, Plate("JRP251")),
                Date()
            )
        )

        //Assert
        assertNull(vehicle)
    }

}