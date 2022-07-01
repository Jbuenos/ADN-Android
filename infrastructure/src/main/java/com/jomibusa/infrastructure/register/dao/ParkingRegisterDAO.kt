package com.jomibusa.infrastructure.register.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity

@Dao
interface ParkingRegisterDAO {

    @Query("SELECT * FROM register")
    fun getAllIVehiclesParking(): List<ParkingRegisterEntity>?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertVehicleParking(vararg parking: ParkingRegisterEntity)

    /*@Query("DELETE FROM parking WHERE num_plate_car = :numPlate or num_plate_motorcycle = :numPlate")
    fun deleteVehicleParking(vararg numPlate: String): Int

    @Query("SELECT * FROM parking WHERE num_plate_car = :numPlate or num_plate_motorcycle = :numPlate")
    fun findVehicleByPlate(vararg numPlate: String): ParkingEntity?*/

}