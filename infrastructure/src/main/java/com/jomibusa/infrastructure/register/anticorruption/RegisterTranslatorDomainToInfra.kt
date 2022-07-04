package com.jomibusa.infrastructure.register.anticorruption

import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity
import com.jomibusa.infrastructure.vehicle.entities.CarEntity

class RegisterTranslatorDomainToInfra {

    fun parseParkingRegisterDomainToEntity(register: Register): ParkingRegisterEntity =
        ParkingRegisterEntity(
            idPlateVehicle = register.vehicle.plate.numPlate,
            initDate = register.initDate.time
        )

}