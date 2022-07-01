package com.jomibusa.infrastructure.vehicle.dao

import androidx.room.*
import com.jomibusa.infrastructure.vehicle.entities.CarEntity

@Dao
interface CarDAO {

    @Query("SELECT * FROM car")
    fun getAllCars(): List<CarEntity?>?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertCar(vararg car: CarEntity)

    @Delete
    fun deleteCar(carEntity: CarEntity): Int

    @Query("SELECT * FROM car WHERE numPlate = :numPlate")
    fun findCarByPlate(vararg numPlate: String): CarEntity?

}