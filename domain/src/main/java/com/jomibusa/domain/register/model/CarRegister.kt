package com.jomibusa.domain.register.model

import com.jomibusa.domain.vehicle.model.Vehicle
import java.util.*

class CarRegister(vehicle: Vehicle, initDate: Date) : Register(vehicle, initDate) {

    override val capacityParking: Int
        get() = 20
}