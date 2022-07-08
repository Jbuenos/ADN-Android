package com.jomibusa.infrastructure.vehicle.dao

import androidx.room.*
import com.jomibusa.infrastructure.shared.relation.ParkingRegisterWithCar
import com.jomibusa.infrastructure.vehicle.entities.CarEntity

@Dao
interface CarDAO {

    @Query("SELECT * FROM car")
    fun getAllCarsFromParking(): List<CarEntity>?

    @Transaction
    @Query("SELECT * FROM car")
    fun getAllCarsAndRegisterFromParking(): List<ParkingRegisterWithCar>?

    @Query("SELECT * FROM car WHERE numPlate = :numPlate")
    fun findCarByPlate(vararg numPlate: String): CarEntity?

    @Transaction
    @Query("SELECT * FROM car WHERE numPlate = :numPlate")
    fun findCarAndRegisterByPlate(vararg numPlate: String): ParkingRegisterWithCar?

}