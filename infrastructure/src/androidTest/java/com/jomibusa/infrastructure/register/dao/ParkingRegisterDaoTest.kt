package com.jomibusa.infrastructure.register.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity
import com.jomibusa.infrastructure.shared.database.ParkingDatabase
import com.jomibusa.infrastructure.vehicle.entities.CarEntity
import junit.framework.TestCase
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class ParkingRegisterDaoTest {

    private lateinit var parkingRegisterDAO: ParkingRegisterDAO
    private lateinit var db: ParkingDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, ParkingDatabase::class.java
        ).build()
        parkingRegisterDAO = db.parkingRegisterDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun saveParkingRegisterAndReadFirstInListTheSameParkingRegister() {

        //Arrange
        val parking =
            ParkingRegisterEntity(UUID.randomUUID().toString().toLong(), "HMT251", 1656950400)

        //Act
        db.parkingRegisterDAO.insertParkingRegister(parking)
        val listParkingRegister = db.parkingRegisterDAO.getAllParkingRegister()
        if (listParkingRegister != null && listParkingRegister.isNotEmpty()) {
            val newParking = listParkingRegister[0]

            //Assert
            MatcherAssert.assertThat(
                newParking.idParkingRegister,
                CoreMatchers.equalTo(parking.idParkingRegister)
            )
        }
    }

    @Test
    @Throws(Exception::class)
    fun saveParkingRegisterAndReadToGetTheSameSize() {

        //Arrange
        val parkingRegister1 =
            ParkingRegisterEntity(UUID.randomUUID().toString().toLong(), "HMT251", 1656950400)
        val parkingRegister2 =
            ParkingRegisterEntity(UUID.randomUUID().toString().toLong(), "UPA19C", 1656950400)
        val parkingRegister3 =
            ParkingRegisterEntity(UUID.randomUUID().toString().toLong(), "JRP310", 1656950400)

        //Act
        db.parkingRegisterDAO.deleteParkingRegister(parkingRegister1)
        db.parkingRegisterDAO.deleteParkingRegister(parkingRegister2)
        db.parkingRegisterDAO.deleteParkingRegister(parkingRegister3)
        val listParkingRegister = db.parkingRegisterDAO.getAllParkingRegister()

        TestCase.assertEquals(3, listParkingRegister?.size)

    }

    @Test
    @Throws(Exception::class)
    fun getAllParkingRegistersAndGetNullList_null() {

        //Arrange

        //Act

        val listParkingRegister = db.parkingRegisterDAO.getAllParkingRegister()

        //Assert
        TestCase.assertNull(listParkingRegister)

    }

    @Test
    @Throws(Exception::class)
    fun saveParkingRegisterAndThenDelete() {

        //Arrange
        val parkingRegister =
            ParkingRegisterEntity(UUID.randomUUID().toString().toLong(), "HMT251", 1656950400)
        db.parkingRegisterDAO.insertParkingRegister(parkingRegister)

        //Act
        db.parkingRegisterDAO.deleteParkingRegister(parkingRegister)
        val listParkingRegister = db.parkingRegisterDAO.getAllParkingRegister()

        //Assert
        TestCase.assertNull(listParkingRegister)

    }

}