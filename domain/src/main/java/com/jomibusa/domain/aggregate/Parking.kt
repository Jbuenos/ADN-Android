package com.jomibusa.domain.aggregate

import com.jomibusa.domain.entity.Vehicle
import java.util.*

data class Parking(val vehicle: Vehicle, val date: Date)
