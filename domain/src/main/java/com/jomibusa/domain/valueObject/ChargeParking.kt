package com.jomibusa.domain.valueObject

import com.jomibusa.domain.entity.Vehicle
import com.jomibusa.domain.exception.NegativeDayException
import com.jomibusa.domain.exception.RangeHourException

class ChargeParking {

    fun validateTotalService(numDays: Int = 0, numHours: Int = 0, vehicle: Vehicle): Double {
        if (numDays >= 0) {
            if (numHours in 0..24) {
                val totalServiceDay = vehicle.costByDay() * numDays
                val totalServiceHour = vehicle.costByHour() * numHours
                return totalServiceDay + totalServiceHour
            } else {
                throw RangeHourException()
            }
        } else {
            throw NegativeDayException()
        }
    }

}