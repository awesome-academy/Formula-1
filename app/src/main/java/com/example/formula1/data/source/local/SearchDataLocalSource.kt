package com.example.formula1.data.source.local

import com.example.formula1.data.model.driverstanding.Driver
import com.example.formula1.data.source.SearchDataSource

class SearchDataLocalSource(private val driverDAO: DriverDAO) : SearchDataSource.Local {

    override suspend fun getDriverHistory(): List<Driver> {
        return driverDAO.getHistoryDriver()
    }

    override suspend fun insertDriver(driver: Driver) {
        driverDAO.insert(driver)
    }
}
