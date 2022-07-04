package com.jomibusa.infrastructure.vehicle.dao

import androidx.room.*
import com.jomibusa.infrastructure.shared.relation.ParkingRegisterWithMotorcycle
import com.jomibusa.infrastructure.vehicle.entities.MotorcycleEntity

@Dao
interface MotorcycleDAO {

    @Query("SELECT * FROM motorcycle")
    fun getAllMotorcyclesFromParking(): List<MotorcycleEntity>?

    @Transaction
    @Query("SELECT * FROM motorcycle")
    fun getAllMotorcyclesAndRegisterFromParking(): List<ParkingRegisterWithMotorcycle>?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertMotorcycle(vararg motorcycle: MotorcycleEntity)

    @Delete
    fun deleteMotorcycle(vararg motorcycleEntity: MotorcycleEntity): Int

    @Query("SELECT * FROM motorcycle WHERE numPlate = :numPlate")
    fun findMotorcycleByPlate(vararg numPlate: String): MotorcycleEntity?

    @Transaction
    @Query("SELECT * FROM motorcycle WHERE numPlate = :numPlate")
    fun findMotorcycleAndRegisterByPlate(vararg numPlate: String): ParkingRegisterWithMotorcycle?

}