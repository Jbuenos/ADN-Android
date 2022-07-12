package com.jomibusa.adn_android.payment.model

import com.jomibusa.domain.payment.model.CarPayment
import com.jomibusa.domain.payment.model.MotorcyclePayment
import com.jomibusa.domain.payment.model.Payment
import com.jomibusa.domain.payment.service.PaymentService
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.register.service.RegisterService
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Plate
import javax.inject.Inject

class PaymentProvider @Inject constructor(
    private val registerService: RegisterService,
    private val paymentService: PaymentService
) {

    suspend fun calculateService(plate: Plate): Pair<Register?, Double> {
        val register = findRegisterByPlate(plate)
        return if (register != null) {
            val payment = getPayment(register)
            Pair(register, payment.calculateTotalService())
        } else {
            Pair(register, 0.0)
        }
    }

    private suspend fun findRegisterByPlate(plate: Plate): Register? {
        return registerService.findPreviousVehicleRegister(plate)
    }

    private fun getPayment(register: Register): Payment {
        return when (register.vehicle) {
            is Car -> CarPayment(register)
            else -> MotorcyclePayment(register)
        }
    }

    suspend fun payService(register: Register): Int {
        return paymentService.deleteRegister(register)
    }

}