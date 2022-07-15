package com.jomibusa.infrastructure.vehicle.dao

import androidx.room.Dao
import androidx.room.Query
import com.jomibusa.infrastructure.vehicle.entities.CarEntity

@Dao
interface CarDAO {

    @Query("SELECT * FROM car")
    fun getAllCarsFromParking(): List<CarEntity>?

    @Query("SELECT * FROM car WHERE numPlate = :numPlate")
    fun findCarByPlate(vararg numPlate: String): CarEntity?

}