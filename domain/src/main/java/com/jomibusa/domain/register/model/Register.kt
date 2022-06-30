package com.jomibusa.domain.register.model

import com.jomibusa.domain.register.exception.FirstLetterOrDayConditionException
import com.jomibusa.domain.vehicle.model.Vehicle
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

abstract class Register(val vehicle: Vehicle, val initDate: Date) {

    abstract val capacityParking: Int

    init {
        if (isPlateStartWhitA(vehicle) && isDayRestriction(initDate)) {
            throw FirstLetterOrDayConditionException()
        }
    }

    private fun isPlateStartWhitA(vehicle: Vehicle): Boolean {
        return vehicle.plate.numPlate.startsWith("a") || vehicle.plate.numPlate.startsWith("A")
    }

    private fun isDayRestriction(date: Date): Boolean {
        val localDateTime = convertDateToLocalDateTime(date)
        return localDateTime.dayOfWeek != DayOfWeek.MONDAY || localDateTime.dayOfWeek != DayOfWeek.SUNDAY
    }

    private fun convertDateToLocalDateTime(dateToConvert: Date): LocalDateTime {
        return LocalDateTime.ofInstant(
            dateToConvert.toInstant(), ZoneId.systemDefault()
        )
    }

}