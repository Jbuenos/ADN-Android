package com.jomibusa.domain.register.model

import com.jomibusa.domain.vehicle.model.Vehicle
import java.util.*

class MotorcycleRegister(vehicle: Vehicle, initDate: Date) : Register(vehicle, initDate) {

    override val capacityParking: Int
        get() = 10

}