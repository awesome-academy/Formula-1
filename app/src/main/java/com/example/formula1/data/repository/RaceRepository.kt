package com.example.formula1.data.repository

import com.example.formula1.data.model.FastestLapResponse
import com.example.formula1.data.model.FinishPositionResponse
import com.example.formula1.data.model.RaceSearchResponse
import com.example.formula1.data.model.StartingGridResponse

import com.example.formula1.utils.DataResult
import com.example.formula1.utils.RACE_TYPE_DEFAULT

interface RaceRepository {
    suspend fun getRaceResponse(season: String, type: String = RACE_TYPE_DEFAULT): DataResult<RaceSearchResponse>

    suspend fun getStartingGridResponse(raceID: String): DataResult<StartingGridResponse>

    suspend fun getFinishPositionResponse(raceID: String): DataResult<FinishPositionResponse>

    suspend fun getFastestLapResponse(raceID: String): DataResult<FastestLapResponse>
}
