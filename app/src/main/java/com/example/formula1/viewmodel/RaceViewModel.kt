package com.example.formula1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.formula1.data.model.FastestLapResponse
import com.example.formula1.data.model.FinishPositionResponse
import com.example.formula1.data.model.RaceSearchResponse
import com.example.formula1.data.model.StartingGridResponse
import com.example.formula1.data.repository.RaceRepository
import com.example.formula1.utils.base.BaseViewModel

class RaceViewModel(private val repository: RaceRepository) : BaseViewModel() {

    private val _searchResult = MutableLiveData<RaceSearchResponse>()
    val searchResult: LiveData<RaceSearchResponse> get() = _searchResult

    private val _gridResult = MutableLiveData<StartingGridResponse>()
    val gridResult: LiveData<StartingGridResponse> get() = _gridResult

    private val _finishPositionResult = MutableLiveData<FinishPositionResponse>()
    val finishPositionResult: LiveData<FinishPositionResponse> get() = _finishPositionResult

    private val _fastestLapResult = MutableLiveData<FastestLapResponse>()
    val fastestLapResult: LiveData<FastestLapResponse> get() = _fastestLapResult

    fun getRaces(season: String) {
        launchAsync(
            request = { repository.getRaceResponse(season) },
            onSuccess = {
                _searchResult.value = it
            },
            onError = {
                it.message.toString()
            })
    }

    fun getStartingGrid(raceID: String) {
        launchAsync(
            request = { repository.getStartingGridResponse(raceID) },
            onSuccess = {
                _gridResult.value = it
            },
            onError = {
                it.message.toString()
            })
    }

    fun getFinishPosition(raceID: String) {
        launchAsync(
            request = { repository.getFinishPositionResponse(raceID) },
            onSuccess = {
                _finishPositionResult.value = it
            },
            onError = {
                it.message.toString()
            })
    }

    fun getFastestLap(raceID: String) {
        launchAsync(
            request = { repository.getFastestLapResponse(raceID) },
            onSuccess = {
                _fastestLapResult.value = it
            },
            onError = {
                it.message.toString()
            })
    }
}
