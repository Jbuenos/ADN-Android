package com.jomibusa.infrastructure.vehicle.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "motorcycle")
class MotorcycleEntity(
    @PrimaryKey @ColumnInfo(name = "num_plate_motorcycle") val numPlate: String,
    @ColumnInfo(name = "cylinder_capacity") val cylinderCapacity: Int
)