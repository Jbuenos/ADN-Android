package com.jomibusa.adn_android.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jomibusa.domain.vehicle.model.Plate
import com.jomibusa.domain.vehicle.service.VehicleService
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val repository: VehicleService = TODO()


    private var _getError = MutableLiveData<Boolean>()
    val getError: LiveData<Boolean> get() = _getError


    fun insertVehicle(plate: Plate) {
        viewModelScope.launch {

        }
    }

}