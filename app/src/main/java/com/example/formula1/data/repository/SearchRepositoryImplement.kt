package com.example.formula1.data.repository

import com.example.formula1.data.model.DriverSearchResponse
import com.example.formula1.data.model.DriverStandingList
import com.example.formula1.data.model.TeamStandingList
import com.example.formula1.data.source.SearchDataSource
import com.example.formula1.utils.DataResult
import com.example.formula1.utils.base.BaseRepository
import kotlinx.coroutines.CoroutineDispatcher

class SearchRepositoryImplement(private val remote: SearchDataSource.Remote) :
    BaseRepository(),
    SearchRepository {

    override suspend fun getSearchResult(
        id: Int,
        dispatcher: CoroutineDispatcher
    ): DataResult<DriverSearchResponse> {
        return getResult(dispatcher) { remote.getSearchResult(id) }
    }

    override suspend fun getDriverStandingResult(
        year: String,
        dispatcher: CoroutineDispatcher
    ): DataResult<DriverStandingList> {
        return getResult(dispatcher) { remote.getDriverStanding(year) }
    }

    override suspend fun getTeamStandingResult(
        year: String,
        dispatcher: CoroutineDispatcher
    ): DataResult<TeamStandingList> {
        return getResult(dispatcher) { remote.getTeamStanding(year) }
    }
}
