package com.jomibusa.domain.vehicle.model

import com.jomibusa.domain.vehicle.exception.NegativeCylinderCapacityException

class Motorcycle(val cylinderCapacity: Int, val numPlate: Plate) :
    Vehicle(numPlate) {

    init {
        if (validateNegativeCylinderCapacity(cylinderCapacity)) {
            throw NegativeCylinderCapacityException()
        }
    }

    private fun validateNegativeCylinderCapacity(capacity: Int): Boolean = capacity < 0
}
