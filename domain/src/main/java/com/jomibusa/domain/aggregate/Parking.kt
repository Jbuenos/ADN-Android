package com.jomibusa.domain.aggregate

import com.jomibusa.domain.entity.Vehicle
import java.util.*

data class Parking(val listVehicle: List<Vehicle>, val date: Date)
