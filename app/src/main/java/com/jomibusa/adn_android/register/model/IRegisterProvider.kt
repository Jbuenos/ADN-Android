package com.jomibusa.adn_android.register.model

import com.jomibusa.domain.vehicle.model.Plate

interface IRegisterProvider {

    suspend fun insertNewRegister(plate: Plate)

}