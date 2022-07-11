package com.jomibusa.adn_android.register.model

import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.infrastructure.register.repository.RoomCarParkingRegisterRepository
import com.jomibusa.infrastructure.register.repository.RoomMotorcycleParkingRegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RegisterModule {

    @Binds
    abstract fun bindRegisterCarRepository(roomRepository: RoomCarParkingRegisterRepository): RegisterRepository

    @Binds
    abstract fun bindRegisterMotorcycleRepository(roomRepository: RoomMotorcycleParkingRegisterRepository): RegisterRepository

}
