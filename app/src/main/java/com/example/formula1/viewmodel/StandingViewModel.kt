package com.example.formula1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.formula1.data.model.DriverSearchResponse
import com.example.formula1.data.model.DriverStandingList
import com.example.formula1.data.model.TeamStandingList
import com.example.formula1.data.model.driverstanding.Driver
import com.example.formula1.data.repository.SearchRepository
import com.example.formula1.utils.base.BaseViewModel
import kotlinx.coroutines.Dispatchers

class StandingViewModel(private val repository: SearchRepository) : BaseViewModel() {

    private val _searchResult = MutableLiveData<DriverSearchResponse>()
    val searchResult: LiveData<DriverSearchResponse> get() = _searchResult

    private val _driverList = MutableLiveData<DriverStandingList>()
    val driverList: LiveData<DriverStandingList> get() = _driverList

    private val _isAddedToLocal = MutableLiveData<Boolean>()
    val isAddedToLocal: LiveData<Boolean> get() = _isAddedToLocal

    private val _driverLocalList = MutableLiveData<List<Driver>>()
    val driverLocalList: LiveData<List<Driver>> get() = _driverLocalList

    private val _teamList = MutableLiveData<TeamStandingList>()
    val teamList: LiveData<TeamStandingList> get() = _teamList

    fun searchDriver(id: Int) {
        launchAsync(
            request = { repository.getSearchResult(id, Dispatchers.IO) },
            onSuccess = {
                _searchResult.value = it
            },
            onError = {
                it.message.toString()
            })
    }

    fun getDriverStanding(year: String) {
        launchAsync(
            request = { repository.getDriverStandingResult(year, Dispatchers.IO) },
            onSuccess = {
                _driverList.value = it
            },
            onError = {
                it.message.toString()
            })
    }

    fun getTeamStanding(year: String) {
        launchAsync(
            request = { repository.getTeamStandingResult(year, Dispatchers.IO) },
            onSuccess = {
                _teamList.value = it
            },
            onError = {
                it.message.toString()
            })
    }

    fun getDriverLocal() {
        launchAsync(
            request = { repository.getDriver(Dispatchers.IO) },
            onSuccess = {
                _driverLocalList.value = it
            },
            onError = {
                it.message.toString()
            }
        )
    }

    fun insertDriverLocal(driver: Driver) {
        launchAsync(
            request = { repository.insertDriver(driver, Dispatchers.IO) },
            onSuccess = { _isAddedToLocal.value = true },
            onError = {
                it.message.toString()
            }
        )
    }
}
