package com.example.formula1.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.formula1.data.model.driverstanding.Driver

@Dao
interface DriverDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(driver: Driver)

    @Query("SELECT * FROM ${DriverHistoryDatabase.DRIVER_TABLE}")
    suspend fun getHistoryDriver(): List<Driver>

}
