package com.jomibusa.domain.payservice.repository

import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.vehicle.model.Plate

interface PayServiceRepository {

    suspend fun findRegisterToPayService(plate: Plate): Register?

    suspend fun deleteRegisterByPlate(plate: Plate): Int

}