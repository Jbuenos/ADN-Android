package com.jomibusa.infrastructure.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jomibusa.infrastructure.entities.CarEntity

@Dao
interface CarDAO {

    @Query("SELECT * FROM car_table")
    suspend fun getAllCars(): List<CarEntity>?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertCar(vararg car: CarEntity)

    @Query("DELETE FROM car_table WHERE num_plate_car = :numPlate")
    fun deleteCar(vararg numPlate: String)

    @Query("SELECT * FROM car_table WHERE num_plate_car = :numPlate")
    fun findCarByPlate(vararg numPlate: String): CarEntity?

}