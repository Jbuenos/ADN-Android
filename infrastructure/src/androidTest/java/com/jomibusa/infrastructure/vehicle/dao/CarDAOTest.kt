package com.jomibusa.infrastructure.vehicle.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity
import com.jomibusa.infrastructure.shared.database.ParkingDatabase
import com.jomibusa.infrastructure.vehicle.entities.CarEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@SmallTest
class CarDAOTest {

    private lateinit var carDAO: CarDAO
    private lateinit var db: ParkingDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, ParkingDatabase::class.java
        ).build()
        carDAO = db.carDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun saveCarAndReadFirstInListTheSameCar() {

        //Arrange
        val car = CarEntity("HMT251")

        //Act
        db.carDAO.insertCar(car)
        val listCars = db.carDAO.getAllCarsFromParking()
        if (listCars != null && listCars.isNotEmpty()) {
            val newCar = listCars[0]

            //Assert
            assertThat(newCar.numPlate, CoreMatchers.equalTo(car.numPlate))
        }
    }

    @Test
    @Throws(Exception::class)
    fun saveCarsAndReadToGetTheExactSize() {

        //Arrange
        val car1 = CarEntity("HMT251")
        val car2 = CarEntity("HMT252")
        val car3 = CarEntity("HMT253")

        //Act
        db.carDAO.insertCar(car1)
        db.carDAO.insertCar(car2)
        db.carDAO.insertCar(car3)
        val listCars = db.carDAO.getAllCarsFromParking()

        assertEquals(3, listCars?.size)

    }

    @Test
    @Throws(Exception::class)
    fun getAllCarsAndGetEmptyList_empty() {

        //Arrange

        //Act

        val listCars = db.carDAO.getAllCarsFromParking()

        //Assert
        assertEquals(0, listCars?.size)

    }

    @Test
    @Throws(Exception::class)
    fun saveCarAndThenDelete() {

        //Arrange
        val car = CarEntity("HMT251")
        val car2 = CarEntity("HMT252")
        val car3 = CarEntity("HMT253")
        db.carDAO.insertCar(car)
        db.carDAO.insertCar(car2)
        db.carDAO.insertCar(car3)

        //Act
        db.carDAO.deleteCar(car3)
        val listCars = db.carDAO.getAllCarsFromParking()

        //Assert
        assertEquals(2, listCars?.size)

    }

    @Test
    @Throws(Exception::class)
    fun saveCarAndFindByPlate_success() {

        //Arrange
        val car = CarEntity("HMT251")

        //Act
        db.carDAO.insertCar(car)
        val carFound = db.carDAO.findCarByPlate("HMT251")

        if (carFound != null) {

            //Assert
            assertThat(carFound.numPlate, CoreMatchers.equalTo(car.numPlate))
        }
    }

    @Test
    @Throws(Exception::class)
    fun saveCarAndFindByPlate_emptyResult() {

        //Arrange
        val car = CarEntity("HMT251")

        //Act
        db.carDAO.insertCar(car)
        val carFound = db.carDAO.findCarByPlate("HMT252")

        //Assert
        assertNull(carFound)
    }

    @Test
    @Throws(Exception::class)
    fun saveCarAndRegisterAndReadFirstInListTheSameResult() {

        //Arrange
        val car = CarEntity("HMT251")
        val carRegister = ParkingRegisterEntity(idPlateVehicle = car.numPlate, initDate = 1657134810)

        //Act
        db.carDAO.insertCar(car)
        db.parkingRegisterDAO.insertParkingRegister(carRegister)
        val listCars = db.carDAO.getAllCarsAndRegisterFromParking()
        if (listCars != null && listCars.isNotEmpty()) {
            val result = listCars[0]

            //Assert
            assertThat(result.parkingRegisterEntity.idPlateVehicle, CoreMatchers.equalTo(car.numPlate))
        }
    }

}