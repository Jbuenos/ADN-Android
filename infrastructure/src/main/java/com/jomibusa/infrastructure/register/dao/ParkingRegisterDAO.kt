package com.jomibusa.infrastructure.register.dao

import androidx.room.*
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity
import com.jomibusa.infrastructure.shared.relation.ParkingRegisterWithCar
import com.jomibusa.infrastructure.shared.relation.ParkingRegisterWithMotorcycle
import com.jomibusa.infrastructure.vehicle.entities.CarEntity
import com.jomibusa.infrastructure.vehicle.entities.MotorcycleEntity

@Dao
interface ParkingRegisterDAO {

    @Query("SELECT * FROM register")
    fun getAllParkingRegister(): List<ParkingRegisterEntity>?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertParkingRegister(vararg parking: ParkingRegisterEntity)

    @Delete
    fun deleteParkingRegister(vararg parkingRegisterEntity: ParkingRegisterEntity): Int

    @Query("SELECT * FROM register WHERE idPlateVehicle = :numPlate")
    fun findRegisterByPlate(vararg numPlate: String): ParkingRegisterEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertCar(vararg car: CarEntity)

    @Delete
    fun deleteCar(vararg carEntity: CarEntity): Int

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertMotorcycle(vararg motorcycle: MotorcycleEntity)

    @Delete
    fun deleteMotorcycle(vararg motorcycleEntity: MotorcycleEntity): Int

    @Transaction
    fun saveRegisterWithCar(withCars: ParkingRegisterWithCar) {
        insertCar(withCars.carEntity)
        insertParkingRegister(withCars.parkingRegisterEntity)
    }

    @Transaction
    fun saveRegisterWithMotorcycle(withMotorcycle: ParkingRegisterWithMotorcycle) {
        insertMotorcycle(withMotorcycle.motorcycleEntity)
        insertParkingRegister(withMotorcycle.parkingRegisterEntity)
    }

    @Transaction
    fun deleteRegisterWithCar(withCars: ParkingRegisterWithCar) {
        deleteCar(withCars.carEntity)
        deleteParkingRegister(withCars.parkingRegisterEntity)
    }

    @Transaction
    fun deleteRegisterWithMotorcycle(withMotorcycle: ParkingRegisterWithMotorcycle) {
        deleteMotorcycle(withMotorcycle.motorcycleEntity)
        deleteParkingRegister(withMotorcycle.parkingRegisterEntity)
    }

    @Transaction
    @Query("SELECT * FROM car")
    fun getAllCarsAndRegisterFromParking(): List<ParkingRegisterWithCar>?

    @Transaction
    @Query("SELECT * FROM motorcycle")
    fun getAllMotorcyclesAndRegisterFromParking(): List<ParkingRegisterWithMotorcycle>?

    @Transaction
    @Query("SELECT * FROM car WHERE numPlate = :numPlate")
    fun findCarAndRegisterByPlate(vararg numPlate: String): ParkingRegisterWithCar?

    @Transaction
    @Query("SELECT * FROM motorcycle WHERE numPlate = :numPlate")
    fun findMotorcycleAndRegisterByPlate(vararg numPlate: String): ParkingRegisterWithMotorcycle?

}