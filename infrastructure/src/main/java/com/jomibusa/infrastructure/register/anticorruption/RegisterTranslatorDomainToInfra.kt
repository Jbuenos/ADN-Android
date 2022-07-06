package com.jomibusa.infrastructure.register.anticorruption

import com.jomibusa.domain.register.model.Register
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity

object RegisterTranslatorDomainToInfra {

    fun parseParkingRegisterDomainToEntity(register: Register): ParkingRegisterEntity =
        ParkingRegisterEntity(
            idPlateVehicle = register.vehicle.plate.numPlate,
            initDate = register.initDate.time
        )

}