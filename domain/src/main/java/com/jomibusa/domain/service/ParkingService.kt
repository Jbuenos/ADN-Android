package com.jomibusa.domain.service

import com.jomibusa.domain.aggregate.Parking
import com.jomibusa.domain.entity.Vehicle
import com.jomibusa.domain.exception.PlateVehicleNotFoundException
import com.jomibusa.domain.repository.IParkingRepository
import com.jomibusa.domain.valueObject.ChargeParking
import java.text.SimpleDateFormat
import java.util.*

class ParkingService(
    private val repository: IParkingRepository,
    private val maxNumOfCars: Int = 20,
    private val maxNumOfMotorcycle: Int = 10,
    private val chargeParking: ChargeParking
) {

    suspend fun isThereSpaceToCar(): Boolean {
        return if (repository.getNumOfCars() != null) {
            return repository.getNumOfCars()!! > maxNumOfCars
        } else {
            false
        }
    }

    suspend fun isThereSpaceToMotorcycle(): Boolean {
        return if (repository.getNumOfMotorcycles() != null) {
            return repository.getNumOfMotorcycles()!! > maxNumOfMotorcycle
        } else {
            false
        }
    }

    fun isPlateStartWhitA(vehicle: Vehicle): Boolean {
        return vehicle.plate.numPlate.startsWith("a") || vehicle.plate.numPlate.startsWith("A")
    }

    fun validateDayWithPlate(parking: Parking) {
        val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
        val dayOfTheWeek: String = sdf.format(parking.date)
        //TODO Falta implementar la lógica para validar los días
    }

    suspend fun getCostForVehicle(vehicle: Vehicle, numOfDays: Int, numHours: Int): Double {
        val vehicleByPlate =
            repository.findVehicleFromParking(vehicle) ?: throw PlateVehicleNotFoundException()
        return chargeParking.validateTotalService(numOfDays, numHours, vehicleByPlate.vehicle)
    }

}