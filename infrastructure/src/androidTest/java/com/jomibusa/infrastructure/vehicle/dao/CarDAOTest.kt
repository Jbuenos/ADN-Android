package com.jomibusa.infrastructure.vehicle.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.jomibusa.infrastructure.shared.database.ParkingDatabase
import com.jomibusa.infrastructure.vehicle.entities.CarEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
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
    fun saveCarsAndReadToGetTheSameSize() {

        //Arrange
        val car1 = CarEntity("HMT251")
        val car2 = CarEntity("HMT251")
        val car3 = CarEntity("HMT251")

        //Act
        db.carDAO.insertCar(car1)
        db.carDAO.insertCar(car2)
        db.carDAO.insertCar(car3)
        val listCars = db.carDAO.getAllCarsFromParking()

        assertEquals(3, listCars?.size)

    }

    @Test
    @Throws(Exception::class)
    fun getAllCarsAndGetNullList_null() {

        //Arrange

        //Act

        val listCars = db.carDAO.getAllCarsFromParking()

        //Assert
        assertNull(listCars)

    }

    @Test
    @Throws(Exception::class)
    fun saveCarAndThenDelete() {

        //Arrange
        val car = CarEntity("HMT251")
        db.carDAO.insertCar(car)

        //Act
        db.carDAO.deleteCar(car)
        val listCars = db.carDAO.getAllCarsFromParking()

        //Assert
        assertNull(listCars)

    }

}