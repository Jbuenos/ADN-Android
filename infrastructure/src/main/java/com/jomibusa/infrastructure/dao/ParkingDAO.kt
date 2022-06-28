package com.jomibusa.infrastructure.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jomibusa.infrastructure.entities.CarEntity
import com.jomibusa.infrastructure.entities.ParkingEntity

@Dao
interface ParkingDAO {

    @Query("SELECT * FROM parking_table")
    suspend fun getAllIVehiclesParking(): List<ParkingEntity>?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertVehicleParking(vararg parking: ParkingEntity)

    @Query("DELETE FROM parking_table WHERE num_plate_car = :numPlate or num_plate_motorcycle = :numPlate")
    fun deleteVehicleParking(vararg numPlate: String)

    @Query("SELECT * FROM parking_table WHERE num_plate_car = :numPlate or num_plate_motorcycle = :numPlate")
    fun findVehicleByPlate(vararg numPlate: String): CarEntity?

}