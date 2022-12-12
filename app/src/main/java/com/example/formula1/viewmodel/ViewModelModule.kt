package com.example.formula1.viewmodel

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val ViewModelModule = module {
    viewModel { StandingViewModel(get()) }
    viewModel { TeamDetailViewModel(get()) }
    viewModel { CircuitViewModel(get()) }
    viewModel { RaceViewModel(get()) }
}
