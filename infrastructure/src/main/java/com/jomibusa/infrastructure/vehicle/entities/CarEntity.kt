package com.jomibusa.infrastructure.vehicle.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car")
class CarEntity(
    @PrimaryKey @ColumnInfo(name = "num_plate_car") val numPlate: String
)