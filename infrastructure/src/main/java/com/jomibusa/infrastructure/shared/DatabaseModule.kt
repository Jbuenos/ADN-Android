package com.jomibusa.infrastructure.shared

import android.content.Context
import androidx.room.Room
import com.jomibusa.infrastructure.shared.database.ParkingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideParkingRegisterDAO(roomDatabase: ParkingDatabase) = roomDatabase.parkingRegisterDAO()


    @Singleton
    @Provides
    fun provideParkingDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            ParkingDatabase::class.java,
            "parking.db"
        ).build()

}
