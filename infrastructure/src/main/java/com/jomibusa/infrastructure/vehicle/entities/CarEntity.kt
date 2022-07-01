package com.jomibusa.infrastructure.vehicle.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car")
class CarEntity(
    @PrimaryKey val numPlate: String,
)