package com.jomibusa.domain.entity

import com.jomibusa.domain.valueObject.Plate
import com.jomibusa.domain.valueObject.TypeVehicle

data class Car(val numPlate: Plate) : Vehicle(numPlate, TypeVehicle.CAR) {

    override fun costByDay(): Double = 8000.0

    override fun costByHour(): Double = 1000.0
}
