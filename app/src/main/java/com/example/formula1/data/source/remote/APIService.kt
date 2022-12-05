package com.example.formula1.data.source.remote

import com.example.formula1.data.model.DriverSearchResponse
import com.example.formula1.data.model.DriverStandingList
import com.example.formula1.data.model.TeamStandingList
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    companion object {
        const val API_DRIVER_SEARCH = "drivers"
        const val API_DRIVER_STANDING = "rankings/drivers"
        const val API_TEAM_STANDING = "rankings/teams"
    }

    @GET(API_DRIVER_SEARCH)
    suspend fun getSearchDriver(
        @Query("id") id: Int
    ): DriverSearchResponse

    @GET(API_DRIVER_STANDING)
    suspend fun getDriverStanding(
        @Query("season") year: String,
    ): DriverStandingList

    @GET(API_TEAM_STANDING)
    suspend fun getTeamStanding(
        @Query("season") year: String
    ): TeamStandingList
}
