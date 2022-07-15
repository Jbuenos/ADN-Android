package com.jomibusa.adn_android.payment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jomibusa.adn_android.payment.model.PaymentProvider
import com.jomibusa.adn_android.payment.model.VehicleType
import com.jomibusa.adn_android.register.di.IoDispatcher
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.vehicle.model.Plate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val provider: PaymentProvider
) : ViewModel() {

    private var _getError = MutableLiveData<Boolean>()
    val getError: LiveData<Boolean> get() = _getError

    private var _getValueService = MutableLiveData<Pair<Register?, Double>>()
    val getValueService: LiveData<Pair<Register?, Double>> get() = _getValueService

    private var _doPayment = MutableLiveData<Int>()
    val doPayment: LiveData<Int> get() = _doPayment

    fun calculateService(vehicleType: VehicleType, numPlate: String) {
        viewModelScope.launch(dispatcher) {
            try {
                val plate = Plate(numPlate)
                _getValueService.postValue(provider.calculateService(vehicleType, plate))
            } catch (e: Exception) {
                Log.e("TEST_ERROR", "Error: $e")
                _getError.postValue(true)
            }
        }
    }

    fun doPayment(vehicleType: VehicleType, register: Register) {
        viewModelScope.launch(dispatcher) {
            try {
                _doPayment.postValue(provider.payService(vehicleType, register))
            } catch (e: Exception) {
                Log.e("TEST_ERROR", "Error: $e")
                _getError.postValue(true)
            }
        }
    }


}