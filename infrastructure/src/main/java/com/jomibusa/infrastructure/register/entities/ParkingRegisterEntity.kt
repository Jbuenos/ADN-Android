package com.jomibusa.infrastructure.register.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "register")
class ParkingRegisterEntity(
    @PrimaryKey(autoGenerate = true) val idParkingRegister: Long?= null,
    val idPlateVehicle: String,
    val initDate: Long
)