package com.jomibusa.adn_android.payment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jomibusa.adn_android.payment.model.PaymentProvider
import com.jomibusa.domain.register.model.Register
import com.jomibusa.domain.vehicle.model.Plate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val provider: PaymentProvider) : ViewModel() {

    private var _getError = MutableLiveData<Boolean>()
    val getError: LiveData<Boolean> get() = _getError

    private var _getValueService = MutableLiveData<Pair<Register?, Double>>()
    val getValueService: LiveData<Pair<Register?, Double>> get() = _getValueService

    private var _doPayment = MutableLiveData<Boolean>()
    val doPayment: LiveData<Boolean> get() = _doPayment

    fun calculateService(numPlate: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val plate = Plate(numPlate)
                _getValueService.postValue(provider.calculateService(plate))
            } catch (e: Exception) {
                Log.e("TEST_ERROR", "Error: $e")
                _getError.postValue(true)
            }
        }
    }

    fun doPayment(register: Register) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                provider.payService(register)
                _doPayment.postValue(true)
            } catch (e: Exception) {
                Log.e("TEST_ERROR", "Error: $e")
                _getError.postValue(true)
            }
        }
    }


}