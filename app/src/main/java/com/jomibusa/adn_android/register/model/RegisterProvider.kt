package com.jomibusa.adn_android.register.model

import com.jomibusa.domain.register.service.RegisterService
import com.jomibusa.domain.vehicle.model.Plate
import javax.inject.Inject

class RegisterProvider @Inject constructor(private val registerService: RegisterService) {

    suspend fun insertNewRegister(plate: Plate) {

    }
}