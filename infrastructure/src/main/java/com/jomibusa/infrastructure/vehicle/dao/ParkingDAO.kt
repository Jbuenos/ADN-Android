package com.jomibusa.infrastructure.vehicle.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jomibusa.infrastructure.vehicle.entities.ParkingEntity

@Dao
interface ParkingDAO {

    @Query("SELECT * FROM parking")
    fun getAllIVehiclesParking(): List<ParkingEntity>?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertVehicleParking(vararg parking: ParkingEntity)

    @Query("DELETE FROM parking WHERE num_plate_car = :numPlate or num_plate_motorcycle = :numPlate")
    fun deleteVehicleParking(vararg numPlate: String): Int

    @Query("SELECT * FROM parking WHERE num_plate_car = :numPlate or num_plate_motorcycle = :numPlate")
    fun findVehicleByPlate(vararg numPlate: String): ParkingEntity?

}