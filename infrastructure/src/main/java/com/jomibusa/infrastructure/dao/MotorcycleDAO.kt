package com.jomibusa.infrastructure.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jomibusa.infrastructure.entities.CarEntity
import com.jomibusa.infrastructure.entities.MotorCycleEntity

@Dao
interface MotorcycleDAO {

    @Query("SELECT * FROM motorcycle_table")
    fun getAllMotorcycle(): List<MotorCycleEntity>?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertMotorcycle(vararg motorcycle: MotorCycleEntity)

    @Query("DELETE FROM motorcycle_table WHERE num_plate_motorcycle = :numPlate")
    fun deleteMotorcycle(vararg numPlate: String): Int

    @Query("SELECT * FROM motorcycle_table WHERE num_plate_motorcycle = :numPlate")
    fun findMotorcycleByPlate(vararg numPlate: String): MotorCycleEntity?

}