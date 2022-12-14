package com.example.formula1.data.source.remote

import com.example.formula1.data.model.DriverSearchResponse
import com.example.formula1.data.model.DriverStandingList
import com.example.formula1.data.model.TeamSearchResponse
import com.example.formula1.data.model.TeamStandingList
import com.example.formula1.data.source.SearchDataSource

class SearchDataRemoteSource(private val apiService: APIService) : SearchDataSource.Remote {

    override suspend fun getSearchResult(id: Int): DriverSearchResponse {
        return apiService.getSearchDriver(id)
    }

    override suspend fun getDriverStanding(year: String): DriverStandingList {
        return apiService.getDriverStanding(year)
    }

    override suspend fun getTeamStanding(year: String): TeamStandingList {
        return apiService.getTeamStanding(year)
    }

    override suspend fun getTeamSearchResult(id: Int): TeamSearchResponse {
        return apiService.getSearchTeam(id)
    }

    override suspend fun getSearchByTeamResult(teamID: Int, season: String): DriverStandingList {
        return apiService.getSearchDriverByTeam(teamID, season)
    }

}
