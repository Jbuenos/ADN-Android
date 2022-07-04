package com.jomibusa.domain.register.repository

import com.jomibusa.domain.register.model.CarRegister
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate

interface CarRegisterRepository : RegisterRepository {

    suspend fun getAllCarsRegister(): List<CarRegister>

    suspend fun findCarRegisterByPlate(plate: Plate): CarRegister?

}