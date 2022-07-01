package com.jomibusa.infrastructure.vehicle.dao

import androidx.room.*
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.infrastructure.vehicle.entities.MotorcycleEntity

@Dao
interface MotorcycleDAO {

    @Query("SELECT * FROM motorcycle")
    fun getAllMotorcycle(): List<MotorcycleEntity>?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertMotorcycle(vararg motorcycle: MotorcycleEntity)

    @Delete
    fun deleteMotorcycle(motorcycleEntity: MotorcycleEntity): Int

    @Query("SELECT * FROM motorcycle WHERE numPlate = :numPlate")
    fun findMotorcycleByPlate(vararg numPlate: String): MotorcycleEntity?

}