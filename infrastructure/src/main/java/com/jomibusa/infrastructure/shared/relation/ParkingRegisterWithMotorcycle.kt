package com.jomibusa.infrastructure.shared.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity
import com.jomibusa.infrastructure.vehicle.entities.MotorcycleEntity

data class ParkingRegisterWithMotorcycle(
    @Embedded val motorcycleEntity: MotorcycleEntity,
    @Relation(
        parentColumn = "numPlate",
        entityColumn = "idPlateVehicle"
    ) val parkingRegisterEntity: ParkingRegisterEntity
)