package com.jomibusa.adn_android.register.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jomibusa.adn_android.register.model.RegisterProvider
import com.jomibusa.domain.vehicle.model.Vehicle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val provider: RegisterProvider) : ViewModel() {

    private var _getResultNewRegister = MutableLiveData<Boolean>()
    val getResultNewRegister: LiveData<Boolean> get() = _getResultNewRegister

    private var _getError = MutableLiveData<Exception>()
    val getError: LiveData<Exception> get() = _getError

    fun insertNewRegister(vehicle: Vehicle) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                provider.insertNewRegister(vehicle)
                _getResultNewRegister.postValue(true)
            } catch (e: Exception) {
                Log.e("TEST_ERROR", "Error: ${e.message}")
                _getError.postValue(e)
            }
        }
    }

}