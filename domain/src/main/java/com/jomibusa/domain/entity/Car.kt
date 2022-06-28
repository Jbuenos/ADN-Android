package com.jomibusa.domain.entity

import com.jomibusa.domain.valueObject.Plate

data class Car(val numPlate: Plate) : Vehicle(numPlate) {

    override fun costByDay(): Double = 8000.0

    override fun costByHour(): Double = 1000.0
}
