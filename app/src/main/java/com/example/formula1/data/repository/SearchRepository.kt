package com.example.formula1.data.repository

import com.example.formula1.data.model.DriverSearchResponse
import com.example.formula1.data.model.DriverStandingList
import com.example.formula1.data.model.TeamStandingList
import com.example.formula1.utils.DataResult
import kotlinx.coroutines.CoroutineDispatcher

interface SearchRepository {
    suspend fun getSearchResult(id: Int, dispatcher: CoroutineDispatcher): DataResult<DriverSearchResponse>

    suspend fun getDriverStandingResult(year: String, dispatcher: CoroutineDispatcher): DataResult<DriverStandingList>

    suspend fun getTeamStandingResult(year: String, dispatcher: CoroutineDispatcher): DataResult<TeamStandingList>
}
