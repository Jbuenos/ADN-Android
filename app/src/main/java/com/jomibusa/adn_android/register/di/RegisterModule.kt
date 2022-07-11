package com.jomibusa.adn_android.register.di

import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.infrastructure.register.repository.RoomCarParkingRegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RegisterModule {

    @Binds
    abstract fun bindRegisterCarRepository(roomRepository: RoomCarParkingRegisterRepository): RegisterRepository

    /*@Binds
    abstract fun bindRegisterMotorcycleRepository(roomRepository: RoomMotorcycleParkingRegisterRepository): RegisterRepository*/

}
