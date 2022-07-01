package com.jomibusa.infrastructure.shared.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jomibusa.infrastructure.vehicle.dao.CarDAO
import com.jomibusa.infrastructure.vehicle.dao.MotorcycleDAO
import com.jomibusa.infrastructure.register.dao.ParkingRegisterDAO
import com.jomibusa.infrastructure.vehicle.entities.CarEntity
import com.jomibusa.infrastructure.vehicle.entities.MotorcycleEntity
import com.jomibusa.infrastructure.register.entities.ParkingRegisterEntity

@Database(
    entities = [CarEntity::class, MotorcycleEntity::class, ParkingRegisterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ParkingDatabase : RoomDatabase() {

    abstract val carDAO: CarDAO
    abstract val motorcycleDAO: MotorcycleDAO
    abstract val parkingDAO: ParkingRegisterDAO

    companion object {

        @Volatile
        private var instance: ParkingDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            ParkingDatabase::class.java, "parking.db"
        ).build()

    }

}