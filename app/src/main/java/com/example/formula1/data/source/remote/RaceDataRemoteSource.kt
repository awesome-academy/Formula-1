package com.example.formula1.data.source.remote

import com.example.formula1.data.model.FastestLapResponse
import com.example.formula1.data.model.FinishPositionResponse
import com.example.formula1.data.model.RaceSearchResponse
import com.example.formula1.data.model.StartingGridResponse
import com.example.formula1.data.source.RaceDataSource

class RaceDataRemoteSource(private val apiService: APIService): RaceDataSource.Remote {

    override suspend fun getRaceResponse(season: String, type: String): RaceSearchResponse {
       return apiService.searchRaces(season, type)
    }

    override suspend fun getStartingGrid(raceID: String): StartingGridResponse {
        return apiService.getStartingGrid(raceID)
    }

    override suspend fun getFinishPosition(raceID: String): FinishPositionResponse {
        return apiService.getFinishPosition(raceID)
    }

    override suspend fun getFastestLap(raceID: String): FastestLapResponse {
        return apiService.getFastestLap(raceID)
    }
}
