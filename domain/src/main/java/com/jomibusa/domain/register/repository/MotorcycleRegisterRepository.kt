package com.jomibusa.domain.register.repository

import com.jomibusa.domain.register.model.MotorcycleRegister
import com.jomibusa.domain.vehicle.model.Plate

interface MotorcycleRegisterRepository : RegisterRepository {

    suspend fun getAllMotorcyclesRegister(): List<MotorcycleRegister>

    suspend fun findMotorcycleRegisterByPlate(plate: Plate): MotorcycleRegister?

}