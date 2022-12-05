package com.example.formula1

import android.content.Context
import androidx.room.Room
import com.example.formula1.data.source.local.DriverHistoryDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { provideDriverDatabase(get()) }
    single { provideDriverDAO(get()) }
}

private fun provideDriverDatabase(context: Context): DriverHistoryDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        DriverHistoryDatabase::class.java,
        DriverHistoryDatabase.DRIVER_TABLE
    ).build()
}

private fun provideDriverDAO(driverHistoryDatabase: DriverHistoryDatabase) =
    driverHistoryDatabase.getDriverDAO()
