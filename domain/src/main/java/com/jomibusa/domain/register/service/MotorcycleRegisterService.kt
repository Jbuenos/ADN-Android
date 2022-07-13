package com.jomibusa.domain.register.service

import com.jomibusa.domain.register.repository.RegisterRepository
import javax.inject.Inject

class MotorcycleRegisterService @Inject constructor(@RegisterMotorcycleRepository private val repository: RegisterRepository) :
    RegisterService(repository)