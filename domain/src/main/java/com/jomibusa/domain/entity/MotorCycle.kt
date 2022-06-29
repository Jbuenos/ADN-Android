package com.jomibusa.domain.entity

import com.jomibusa.domain.exception.EmptyPlateException
import com.jomibusa.domain.exception.NegativeCylinderCapacityException
import com.jomibusa.domain.valueObject.Plate
import com.jomibusa.domain.valueObject.TypeVehicle

data class MotorCycle(val cylinderCapacity: Int, val numPlate: Plate) :
    Vehicle(numPlate, TypeVehicle.MOTORCYCLE) {

    init {
        if (validateEmptyPlate(numPlate.numPlate)) {
            throw EmptyPlateException()
        }

        if (validateNegativeCylinderCapacity(cylinderCapacity)) {
            throw NegativeCylinderCapacityException()
        }
    }

    override fun costByDay(): Double {
        return if (cylinderCapacity > 500) 6000.0 else 4000.0
    }

    override fun costByHour(): Double {
        return if (cylinderCapacity > 500) 2500.0 else 500.0
    }

    private fun validateNegativeCylinderCapacity(capacity: Int): Boolean {
        return capacity < 0
    }

}
