package com.jomibusa.infrastructure.register.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity
import com.jomibusa.infrastructure.shared.database.ParkingDatabase
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

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
            ParkingRegisterEntity(idPlateVehicle = "HMT251", initDate = 1656950400)

        //Act
        db.parkingRegisterDAO.insertParkingRegister(parking)
        val listParkingRegister = db.parkingRegisterDAO.getAllParkingRegister()
        if (listParkingRegister != null && listParkingRegister.isNotEmpty()) {
            val newParking = listParkingRegister[0]

            //Assert
            assertThat(
                newParking.idPlateVehicle,
                equalTo(parking.idPlateVehicle)
            )
        }
    }

    @Test
    @Throws(Exception::class)
    fun saveParkingRegisterAndReadToGetTheExactSize() {

        //Arrange
        val parkingRegister1 =
            ParkingRegisterEntity(idPlateVehicle = "HMT251", initDate = 1656950400)
        val parkingRegister2 =
            ParkingRegisterEntity(idPlateVehicle = "UPA19C", initDate = 1656950400)
        val parkingRegister3 =
            ParkingRegisterEntity(idPlateVehicle = "JRP310", initDate = 1656950400)

        //Act
        db.parkingRegisterDAO.insertParkingRegister(parkingRegister1)
        db.parkingRegisterDAO.insertParkingRegister(parkingRegister2)
        db.parkingRegisterDAO.insertParkingRegister(parkingRegister3)
        val listParkingRegister = db.parkingRegisterDAO.getAllParkingRegister()

        assertEquals(3, listParkingRegister?.size)

    }

    @Test
    @Throws(Exception::class)
    fun getAllParkingRegistersAndGetEmptyList_empty() {

        //Arrange

        //Act

        val listParkingRegister = db.parkingRegisterDAO.getAllParkingRegister()

        //Assert
        assertEquals(0, listParkingRegister?.size)

    }

    @Test
    @Throws(Exception::class)
    fun saveParkingRegisterAndThenDelete() {

        //Arrange
        val parkingRegister =
            ParkingRegisterEntity(1L, idPlateVehicle = "HMT251", initDate = 1656950400)
        val parkingRegister2 =
            ParkingRegisterEntity(2L, idPlateVehicle = "HMT252", initDate = 1656950401)
        val parkingRegister3 =
            ParkingRegisterEntity(3L, idPlateVehicle = "HMT253", initDate = 1656950402)
        val parkingRegister4 =
            ParkingRegisterEntity(4L, idPlateVehicle = "HMT254", initDate = 1656950403)

        db.parkingRegisterDAO.insertParkingRegister(parkingRegister)
        db.parkingRegisterDAO.insertParkingRegister(parkingRegister2)
        db.parkingRegisterDAO.insertParkingRegister(parkingRegister3)
        db.parkingRegisterDAO.insertParkingRegister(parkingRegister4)

        //Act
        db.parkingRegisterDAO.deleteParkingRegister(parkingRegister3)
        db.parkingRegisterDAO.deleteParkingRegister(parkingRegister)
        val listParkingRegister = db.parkingRegisterDAO.getAllParkingRegister()

        //Assert
        assertEquals(2, listParkingRegister?.size)

    }

    @Test
    @Throws(Exception::class)
    fun saveParkingRegisterAndFindByPlate_success() {

        //Arrange
        val parkingRegister =
            ParkingRegisterEntity(1L, idPlateVehicle = "HMT251", initDate = 1656950400)

        //Act
        db.parkingRegisterDAO.insertParkingRegister(parkingRegister)
        val parkingRegisterFound = db.parkingRegisterDAO.findRegisterByPlate("HMT251")

        if (parkingRegisterFound != null) {

            //Assert
            assertThat(
                parkingRegisterFound.idPlateVehicle,
                equalTo(parkingRegister.idPlateVehicle)
            )
        }
    }

    @Test
    @Throws(Exception::class)
    fun saveParkingRegisterAndFindByPlate_emptyResult() {

        //Arrange
        val parkingRegister =
            ParkingRegisterEntity(1L, idPlateVehicle = "HMT251", initDate = 1656950400)

        //Act
        db.parkingRegisterDAO.insertParkingRegister(parkingRegister)
        val parkingRegisterFound = db.parkingRegisterDAO.findRegisterByPlate("HMT252")

        //Assert
        assertNull(parkingRegisterFound)
    }

}