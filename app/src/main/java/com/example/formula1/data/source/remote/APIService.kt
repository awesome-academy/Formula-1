package com.example.formula1.data.source.remote

import com.example.formula1.data.model.DriverSearchResponse
import com.example.formula1.data.model.DriverStandingList
import com.example.formula1.data.model.FastestLapResponse
import com.example.formula1.data.model.FinishPositionResponse
import com.example.formula1.data.model.StartingGridResponse
import com.example.formula1.data.model.TeamStandingList
import com.example.formula1.data.model.TeamSearchResponse
import com.example.formula1.data.model.CircuitSearchResponse
import com.example.formula1.data.model.RaceSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    companion object {
        const val API_DRIVER_SEARCH = "drivers"
        const val API_DRIVER_STANDING = "rankings/drivers"
        const val API_TEAM_STANDING = "rankings/teams"
        const val API_TEAM_SEARCH = "teams"
        const val API_CIRCUITS_SEARCH = "circuits"
        const val API_RACE_SEARCH = "races"
        const val API_STARING_GRID = "rankings/startinggrid"
        const val API_FINISH_POSITION = "rankings/races"
        const val API_FASTEST_LAP = "rankings/fastestlaps"
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

    @GET(API_TEAM_SEARCH)
    suspend fun getSearchTeam(
        @Query("id") id: Int
    ): TeamSearchResponse

    @GET(API_DRIVER_STANDING)
    suspend fun getSearchDriverByTeam(
        @Query("team") teamID: Int,
        @Query("season") year: String
    ): DriverStandingList

    @GET(API_CIRCUITS_SEARCH)
    suspend fun getCircuits(): CircuitSearchResponse

    @GET(API_CIRCUITS_SEARCH)
    suspend fun searchCircuits(
        @Query("id") searchKey: String
    ): CircuitSearchResponse

    @GET(API_RACE_SEARCH)
    suspend fun searchRaces(
        @Query("season") season: String,
        @Query("type") raceType: String
    ): RaceSearchResponse

    @GET(API_STARING_GRID)
    suspend fun getStartingGrid(
        @Query("race") raceID: String,
    ): StartingGridResponse

    @GET(API_FINISH_POSITION)
    suspend fun getFinishPosition(
        @Query("race") raceID: String,
    ): FinishPositionResponse

    @GET(API_FASTEST_LAP)
    suspend fun getFastestLap(
        @Query("race") raceID: String,
    ): FastestLapResponse
}
