package com.jomibusa.domain.entity

import com.jomibusa.domain.valueObject.ICostByParking
import com.jomibusa.domain.valueObject.Plate
import com.jomibusa.domain.valueObject.TypeVehicle

abstract class Vehicle(val plate: Plate, val typeVehicle: TypeVehicle) : ICostByParking {

    fun validateEmptyPlate(numPlate: String): Boolean {
        return numPlate.isEmpty()
    }

}
