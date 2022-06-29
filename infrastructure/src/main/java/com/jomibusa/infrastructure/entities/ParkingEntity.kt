package com.jomibusa.infrastructure.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "parking_table")
class ParkingEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_vehicle") val idVehicle: Int? = null,
    @Embedded val carEntity: CarEntity? = null,
    @Embedded val motorCycleEntity: MotorCycleEntity? = null,
    @ColumnInfo(name = "init_date") val date: Long
)