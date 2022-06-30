package com.jomibusa.infrastructure.vehicle.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jomibusa.infrastructure.vehicle.entities.CarEntity

@Dao
interface CarDAO {

    @Query("SELECT * FROM car")
    fun getAllCars(): List<CarEntity?>?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertCar(vararg car: CarEntity)

    @Query("DELETE FROM car WHERE num_plate_car = :numPlate")
    fun deleteCar(vararg numPlate: String): Int

    @Query("SELECT * FROM car WHERE num_plate_car = :numPlate")
    fun findCarByPlate(vararg numPlate: String): CarEntity?

}