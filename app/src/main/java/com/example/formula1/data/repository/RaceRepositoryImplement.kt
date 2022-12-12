package com.example.formula1.data.repository

import com.example.formula1.data.model.FastestLapResponse
import com.example.formula1.data.model.FinishPositionResponse
import com.example.formula1.data.model.RaceSearchResponse
import com.example.formula1.data.model.StartingGridResponse
import com.example.formula1.data.source.RaceDataSource
import com.example.formula1.utils.DataResult
import com.example.formula1.utils.base.BaseRepository

class RaceRepositoryImplement(
    private val remote: RaceDataSource.Remote,
) : BaseRepository(), RaceRepository {

    override suspend fun getRaceResponse(
        season: String,
        type: String
    ): DataResult<RaceSearchResponse> {
        return getResult { remote.getRaceResponse(season, type) }
    }

    override suspend fun getStartingGridResponse(raceID: String): DataResult<StartingGridResponse> {
        return getResult { remote.getStartingGrid(raceID) }
    }

    override suspend fun getFinishPositionResponse(raceID: String): DataResult<FinishPositionResponse> {
        return getResult { remote.getFinishPosition(raceID) }
    }

    override suspend fun getFastestLapResponse(raceID: String): DataResult<FastestLapResponse> {
        return getResult { remote.getFastestLap(raceID) }
    }


}
