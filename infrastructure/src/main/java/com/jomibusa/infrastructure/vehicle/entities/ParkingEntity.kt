package com.jomibusa.infrastructure.vehicle.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parking")
class ParkingEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_vehicle") val idVehicle: Int? = null,
    @Embedded val carEntity: CarEntity? = null,
    @Embedded val motorcycleEntity: MotorcycleEntity? = null,
    @ColumnInfo(name = "init_date") val date: Long
)