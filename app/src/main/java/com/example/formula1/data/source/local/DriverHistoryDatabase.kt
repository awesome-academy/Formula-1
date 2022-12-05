package com.example.formula1.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.formula1.data.model.driverstanding.Driver

@Database(
    entities = [Driver::class],
    version = DriverHistoryDatabase.ROOM_CURRENT_VERSION,
    exportSchema = true
)
abstract class DriverHistoryDatabase : RoomDatabase() {

    abstract fun getDriverDAO(): DriverDAO

    companion object {
        const val ROOM_CURRENT_VERSION = 1
        const val DRIVER_TABLE = "Drivers"
    }
}
