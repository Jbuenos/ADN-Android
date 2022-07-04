package com.jomibusa.infrastructure.register.dao

import androidx.room.*
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity

@Dao
interface ParkingRegisterDAO {

    @Query("SELECT * FROM register")
    fun getAllParkingRegister(): List<ParkingRegisterEntity>?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertParkingRegister(vararg parking: ParkingRegisterEntity)

    @Delete
    fun deleteParkingRegister(vararg parkingRegisterEntity: ParkingRegisterEntity): Int

}