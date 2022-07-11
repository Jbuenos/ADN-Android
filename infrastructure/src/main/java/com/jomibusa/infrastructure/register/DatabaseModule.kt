package com.jomibusa.infrastructure.register

import android.content.Context
import androidx.room.Room
import com.jomibusa.infrastructure.register.dao.ParkingRegisterDAO
import com.jomibusa.infrastructure.shared.database.ParkingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideParkingRegisterDAO(roomDatabase: ParkingDatabase): ParkingRegisterDAO {
        return roomDatabase.parkingRegisterDAO()
    }

    @Provides
    @Singleton
    fun provideParkingDatabase(@ApplicationContext appContext: Context): ParkingDatabase {
        return Room.databaseBuilder(
            appContext,
            ParkingDatabase::class.java,
            "parking.db"
        ).build()
    }

}
