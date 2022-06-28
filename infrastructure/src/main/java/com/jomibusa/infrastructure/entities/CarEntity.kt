package com.jomibusa.infrastructure.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_table")
class CarEntity(
    @PrimaryKey @ColumnInfo(name = "num_plate_car") val numPlate: Int
)