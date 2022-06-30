package com.jomibusa.infrastructure.vehicle.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jomibusa.infrastructure.vehicle.entities.MotorcycleEntity

@Dao
interface MotorcycleDAO {

    @Query("SELECT * FROM motorcycle")
    fun getAllMotorcycle(): List<MotorcycleEntity>?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertMotorcycle(vararg motorcycle: MotorcycleEntity)

    @Query("DELETE FROM motorcycle WHERE num_plate_motorcycle = :numPlate")
    fun deleteMotorcycle(vararg numPlate: String): Int

    @Query("SELECT * FROM motorcycle WHERE num_plate_motorcycle = :numPlate")
    fun findMotorcycleByPlate(vararg numPlate: String): MotorcycleEntity?

}