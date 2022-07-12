package com.jomibusa.adn_android.register.di

import com.jomibusa.domain.register.repository.RegisterRepository
import com.jomibusa.infrastructure.register.repository.RoomCarParkingRegisterRepository
import com.jomibusa.infrastructure.register.repository.RoomMotorcycleParkingRegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
abstract class RegisterModule {

    /*@Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RegisterCarRepository

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RegisterMotorcycleRepository*/

    @Binds
    //@RegisterCarRepository
    abstract fun bindRegisterCarRepository(roomRepository: RoomCarParkingRegisterRepository): RegisterRepository

    /*@Binds
    @RegisterMotorcycleRepository
    abstract fun bindRegisterMotorcycleRepository(roomRepository: RoomMotorcycleParkingRegisterRepository): RegisterRepository*/

}
