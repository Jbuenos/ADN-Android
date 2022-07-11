package com.jomibusa.adn_android.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jomibusa.adn_android.register.model.IRegisterProvider
import com.jomibusa.adn_android.register.model.RegisterProvider
import com.jomibusa.domain.vehicle.model.Plate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val provider: RegisterProvider): ViewModel() {

    private var _getError = MutableLiveData<Boolean>()
    val getError: LiveData<Boolean> get() = _getError

    fun insertNewRegister(plate: Plate) {
        viewModelScope.launch {
            provider.insertNewRegister(plate)
        }
    }

}