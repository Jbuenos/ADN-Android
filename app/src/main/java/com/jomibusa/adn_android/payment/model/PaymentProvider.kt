package com.jomibusa.adn_android.payment.model

import com.jomibusa.domain.payment.model.CarPayment
import com.jomibusa.domain.payment.model.MotorcyclePayment
import com.jomibusa.domain.payment.model.Payment
import com.jomibusa.domain.payment.service.CarPaymentService
import com.jomibusa.domain.payment.service.MotorcyclePaymentService
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.register.service.CarRegisterService
import com.jomibusa.domain.register.service.MotorcycleRegisterService
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import javax.inject.Inject

class PaymentProvider @Inject constructor(
    private val carRegisterService: CarRegisterService,
    private val motorcycleRegisterService: MotorcycleRegisterService,
    private val carPaymentService: CarPaymentService,
    private val motorcyclePaymentService: MotorcyclePaymentService
) {

    suspend fun calculateService(vehicleType: VehicleType, plate: Plate): Pair<Register?, Double> {
        val register = findRegisterByPlate(vehicleType, plate)
        return if (register != null) {
            val payment = getPayment(register)
            Pair(register, payment.calculateTotalService())
        } else {
            Pair(register, 0.0)
        }
    }

    private suspend fun findRegisterByPlate(vehicleType: VehicleType, plate: Plate): Register? {
        return when (vehicleType) {
            VehicleType.CAR -> carRegisterService.findPreviousVehicleRegister(plate)
            VehicleType.MOTORCYCLE -> motorcycleRegisterService.findPreviousVehicleRegister(plate)
        }
    }

    private fun getPayment(register: Register): Payment {
        return when (register.vehicle) {
            is Car -> CarPayment(register)
            else -> MotorcyclePayment(register)
        }
    }

    suspend fun payService(vehicleType: VehicleType, register: Register): Int {
        return when (vehicleType) {
            VehicleType.CAR -> carPaymentService.deleteRegister(register)
            VehicleType.MOTORCYCLE -> motorcyclePaymentService.deleteRegister(register)
        }
    }

}