package com.jomibusa.infrastructure.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jomibusa.infrastructure.entities.CarEntity
import com.jomibusa.infrastructure.entities.MotorCycleEntity
import com.jomibusa.infrastructure.entities.ParkingEntity

@Database(
    entities = [CarEntity::class, MotorCycleEntity::class, ParkingEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ParkingDatabase : RoomDatabase() {

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
        ).allowMainThreadQueries().build()

    }

}