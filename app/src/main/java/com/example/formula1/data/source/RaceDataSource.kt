package com.example.formula1.data.source

import com.example.formula1.data.model.FastestLapResponse
import com.example.formula1.data.model.FinishPositionResponse
import com.example.formula1.data.model.RaceSearchResponse
import com.example.formula1.data.model.StartingGridResponse

interface RaceDataSource {
    interface Remote {
        suspend fun getRaceResponse(season: String, type: String): RaceSearchResponse
        suspend fun getStartingGrid(raceID: String): StartingGridResponse
        suspend fun getFinishPosition(raceID: String): FinishPositionResponse
        suspend fun getFastestLap(raceID: String): FastestLapResponse
    }
}
