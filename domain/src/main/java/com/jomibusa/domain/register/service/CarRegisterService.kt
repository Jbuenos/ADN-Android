package com.jomibusa.domain.register.service

import com.jomibusa.domain.register.repository.RegisterRepository
import javax.inject.Inject

class CarRegisterService @Inject constructor(@RegisterCarRepository private val repository: RegisterRepository) :
    RegisterService(repository)