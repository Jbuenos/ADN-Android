package com.jomibusa.infrastructure.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "motorcycle_table")
class MotorCycleEntity(
    @PrimaryKey @ColumnInfo(name = "num_plate_motorcycle") val numPlate: String,
    @ColumnInfo(name = "cylinder_capacity") val cylinderCapacity: Int
)