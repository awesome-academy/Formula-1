package com.example.formula1

import android.app.Application
import com.example.formula1.data.DataSourceModule
import com.example.formula1.data.NetworkModule
import com.example.formula1.data.RepositoryModule
import com.example.formula1.viewmodel.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val module = listOf(
            NetworkModule,
            ViewModelModule,
            DataSourceModule,
            RepositoryModule,
            databaseModule
        )
        startKoin {
            androidContext(this@MyApplication)
            modules(module)
        }
    }
}
