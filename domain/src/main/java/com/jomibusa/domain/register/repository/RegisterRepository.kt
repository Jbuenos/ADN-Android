package com.jomibusa.domain.register.repository

import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.vehicle.model.Plate

interface RegisterRepository {

    suspend fun insertRegister(register: Register)

    suspend fun deleteRegisterByPlate(register: Register): Int

}