package com.jomibusa.infrastructure.vehicle.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity
import com.jomibusa.infrastructure.shared.database.ParkingDatabase
import com.jomibusa.infrastructure.vehicle.entities.MotorcycleEntity
import junit.framework.TestCase
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MotorcycleDAOTest {

    private lateinit var motorcycleDAO: MotorcycleDAO
    private lateinit var db: ParkingDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, ParkingDatabase::class.java
        ).build()
        motorcycleDAO = db.motorcycleDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun saveMotorcycleAndReadFirstInListTheSameMotorcycle() {

        //Arrange
        val motorcycle = MotorcycleEntity("UPA19C", 450)

        //Act
        db.motorcycleDAO.insertMotorcycle(motorcycle)
        val listMotorcycles = db.motorcycleDAO.getAllMotorcyclesFromParking()
        if (listMotorcycles != null && listMotorcycles.isNotEmpty()) {
            val newMotorcycle = listMotorcycles[0]

            //Assert
            MatcherAssert.assertThat(
                newMotorcycle.numPlate,
                CoreMatchers.equalTo(motorcycle.numPlate)
            )
        }
    }

    @Test
    @Throws(Exception::class)
    fun saveMotorcyclesAndReadToGetTheExactSize() {

        //Arrange
        val motorcycle1 = MotorcycleEntity("UPA19C", 180)
        val motorcycle2 = MotorcycleEntity("JVG09B", 150)
        val motorcycle3 = MotorcycleEntity("JRP34K", 250)

        //Act
        db.motorcycleDAO.insertMotorcycle(motorcycle1)
        db.motorcycleDAO.insertMotorcycle(motorcycle2)
        db.motorcycleDAO.insertMotorcycle(motorcycle3)
        val listMotorcycles = db.motorcycleDAO.getAllMotorcyclesFromParking()

        TestCase.assertEquals(3, listMotorcycles?.size)

    }

    @Test
    @Throws(Exception::class)
    fun getAllMotorcyclesAndGetEmptyList_empty() {

        //Arrange

        //Act

        val listMotorcycles = db.motorcycleDAO.getAllMotorcyclesFromParking()

        //Assert
        TestCase.assertEquals(0, listMotorcycles?.size)

    }

    @Test
    @Throws(Exception::class)
    fun saveMotorcycleAndThenDelete() {

        //Arrange
        val motorcycle = MotorcycleEntity("UPA19C", 250)
        val motorcycle2 = MotorcycleEntity("UPA20C", 250)
        val motorcycle3 = MotorcycleEntity("UPA21C", 250)
        db.motorcycleDAO.insertMotorcycle(motorcycle)
        db.motorcycleDAO.insertMotorcycle(motorcycle2)
        db.motorcycleDAO.insertMotorcycle(motorcycle3)

        //Act
        db.motorcycleDAO.deleteMotorcycle(motorcycle2)
        val listMotorcycles = db.motorcycleDAO.getAllMotorcyclesFromParking()

        //Assert
        TestCase.assertEquals(2, listMotorcycles?.size)

    }

    @Test
    @Throws(Exception::class)
    fun saveMotorcycleAndFindByPlate_success() {

        //Arrange
        val motorcycle = MotorcycleEntity("UPA19C", 250)

        //Act
        db.motorcycleDAO.insertMotorcycle(motorcycle)
        val motorcycleFound = db.motorcycleDAO.findMotorcycleByPlate("UPA19C")

        if (motorcycleFound != null) {

            //Assert
            MatcherAssert.assertThat(
                motorcycleFound.numPlate,
                CoreMatchers.equalTo(motorcycle.numPlate)
            )
        }
    }

    @Test
    @Throws(Exception::class)
    fun saveMotorcycleAndFindByPlate_emptyResult() {

        //Arrange
        val motorcycle = MotorcycleEntity("UPA19C", 450)

        //Act
        db.motorcycleDAO.insertMotorcycle(motorcycle)
        val motorcycleFound = db.motorcycleDAO.findMotorcycleByPlate("UPA20C")

        //Assert
        TestCase.assertNull(motorcycleFound)
    }

    @Test
    @Throws(Exception::class)
    fun saveMotorcycleAndRegisterAndReadFirstInListTheSameResult() {

        //Arrange
        val motorcycle = MotorcycleEntity("UPA19C", 250)
        val motorcycleRegister =
            ParkingRegisterEntity(idPlateVehicle = motorcycle.numPlate, initDate = 1657134810)

        //Act
        db.motorcycleDAO.insertMotorcycle(motorcycle)
        db.parkingRegisterDAO.insertParkingRegister(motorcycleRegister)
        val listMotorcycle = db.motorcycleDAO.getAllMotorcyclesAndRegisterFromParking()
        if (listMotorcycle != null && listMotorcycle.isNotEmpty()) {
            val result = listMotorcycle[0]

            //Assert
            MatcherAssert.assertThat(
                result.parkingRegisterEntity.idPlateVehicle,
                CoreMatchers.equalTo(motorcycle.numPlate)
            )
        }
    }

}