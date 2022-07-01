package com.jomibusa.infrastructure.vehicle.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "motorcycle")
class MotorcycleEntity(
    @PrimaryKey val numPlate: String,
    val cylinderCapacity: Int
)