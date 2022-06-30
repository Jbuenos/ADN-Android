package com.jomibusa.domain.vehicle.model

import com.jomibusa.domain.vehicle.exception.EmptyPlateException
import com.jomibusa.domain.vehicle.exception.InvalidPatternPlateException
import java.util.regex.Pattern

abstract class Vehicle(val plate: Plate) {

    companion object {
        private const val PATTERN_REGEX_PLATE = "[a-zA-Z]{3}[0-9]{2}[a-zA-Z0-9]"
    }

    init {
        if (validateEmptyPlate(plate.numPlate)) {
            throw EmptyPlateException()
        }

        if (!validatePlateRegex(plate.numPlate)) {
            throw InvalidPatternPlateException()
        }
    }

    private fun validateEmptyPlate(numPlate: String): Boolean {
        return numPlate.isEmpty()
    }

    private fun validatePlateRegex(numPlate: String): Boolean {
        return Pattern.matches(PATTERN_REGEX_PLATE, plate.numPlate)
    }

}
