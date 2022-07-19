package com.jomibusa.adn_android.register.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jomibusa.adn_android.register.di.IoDispatcher
import com.jomibusa.adn_android.register.model.RegisterProvider
import com.jomibusa.adn_android.register.model.VehicleRegister
import com.jomibusa.domain.register.exception.CapacityParkingExceededException
import com.jomibusa.domain.register.exception.ExistSameVehicleException
import com.jomibusa.domain.vehicle.exception.InvalidPatternPlateException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val provider: RegisterProvider
) : ViewModel() {

    private var _getResultNewRegister = MutableLiveData<Boolean>()
    val getResultNewRegister: LiveData<Boolean> get() = _getResultNewRegister

    private var _getError = MutableLiveData<String?>()
    val getError: LiveData<String?> get() = _getError

    fun insertNewRegister(typeRegister: VehicleRegister, plate: String, cylinderCapacity: Int = 0) {
        viewModelScope.launch(dispatcher) {
            try {
                provider.insertNewRegister(
                    typeRegister.getNewVehicleToRegister(
                        plate,
                        cylinderCapacity
                    )
                )
                _getResultNewRegister.postValue(true)
            } catch (e: Exception) {
                Log.e("TEST_ERROR", "Error: ${e.message}")
                _getError.postValue(validateTypeException(e))
            }
        }
    }

    private fun validateTypeException(exception: Exception): String? {
        return when (exception) {
            is CapacityParkingExceededException -> exception.message
            is ExistSameVehicleException -> exception.message
            is InvalidPatternPlateException -> exception.message
            else -> null
        }
    }

}