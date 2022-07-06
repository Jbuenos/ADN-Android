package com.jomibusa.domain.vehicle.model

import com.jomibusa.domain.vehicle.exception.InvalidPatternPlateException
import java.util.regex.Pattern

abstract class Vehicle(val plate: Plate) {

    init {
        if (!validatePlateRegex(plate.numPlate)) {
            throw InvalidPatternPlateException()
        }
    }

    private fun validatePlateRegex(numPlate: String): Boolean {
        val patterRegexPlate = "^[a-zA-Z]{3}[0-9]{2}[a-zA-Z0-9]\$"
        return Pattern.matches(patterRegexPlate, numPlate)
    }

}
