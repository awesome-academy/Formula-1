package com.example.formula1.data.source

import com.example.formula1.data.model.DriverSearchResponse
import com.example.formula1.data.model.DriverStandingList
import com.example.formula1.data.model.TeamStandingList
import com.example.formula1.data.model.driverstanding.Driver

interface SearchDataSource {
    interface Remote {
        suspend fun getSearchResult(id: Int): DriverSearchResponse
        suspend fun getDriverStanding(year: String): DriverStandingList
        suspend fun getTeamStanding(year: String): TeamStandingList
    }

    interface Local {
        suspend fun getDriverHistory() : List<Driver>
        suspend fun insertDriver(driver: Driver)
    }
}
