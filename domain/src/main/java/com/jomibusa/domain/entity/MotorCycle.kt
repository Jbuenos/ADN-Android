package com.jomibusa.domain.entity

import com.jomibusa.domain.valueObject.Plate
import com.jomibusa.domain.valueObject.TypeVehicle

data class MotorCycle(val cylinderCapacity: Int, val numPlate: Plate) :
    Vehicle(numPlate, TypeVehicle.MOTORCYCLE) {

    override fun costByDay(): Double {
        return if (cylinderCapacity > 500) 6000.0 else 4000.0
    }

    override fun costByHour(): Double {
        return if (cylinderCapacity > 500) 2500.0 else 500.0
    }
}
