package com.jomibusa.domain.service

import com.jomibusa.domain.repository.IParkingRepository
import com.jomibusa.domain.valueObject.ChargeParking

class ParkingService(
    private val repository: IParkingRepository,
    private val maxNumOfCars: Int = 20,
    private val maxNumOfMotorcycle: Int = 10,
    private val chargeParking: ChargeParking
) {



}