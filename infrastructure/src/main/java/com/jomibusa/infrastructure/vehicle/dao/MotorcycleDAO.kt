package com.jomibusa.infrastructure.vehicle.dao

import androidx.room.*
import com.jomibusa.infrastructure.shared.relation.ParkingRegisterWithMotorcycle
import com.jomibusa.infrastructure.vehicle.entities.MotorcycleEntity

@Dao
interface MotorcycleDAO {

    @Query("SELECT * FROM motorcycle")
    fun getAllMotorcyclesFromParking(): List<MotorcycleEntity>?

    @Query("SELECT * FROM motorcycle WHERE numPlate = :numPlate")
    fun findMotorcycleByPlate(vararg numPlate: String): MotorcycleEntity?

}