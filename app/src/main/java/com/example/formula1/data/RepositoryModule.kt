package com.example.formula1.data

import com.example.formula1.data.repository.CircuitRepository
import com.example.formula1.data.repository.SearchRepository
import com.example.formula1.data.repository.RaceRepository
import com.example.formula1.data.repository.SearchRepositoryImplement
import com.example.formula1.data.repository.CircuitRepositoryImplement
import com.example.formula1.data.repository.RaceRepositoryImplement
import com.example.formula1.data.source.CircuitDataSource
import com.example.formula1.data.source.RaceDataSource
import com.example.formula1.data.source.SearchDataSource
import org.koin.dsl.module

val RepositoryModule = module {
    single { provideSearchRepository(get(), get()) }
    single { provideCircuitRepository(get()) }
    single { provideRaceRepository(get()) }
}

fun provideSearchRepository(
    remote: SearchDataSource.Remote,
    local: SearchDataSource.Local
): SearchRepository {
    return SearchRepositoryImplement(remote, local)
}

fun provideCircuitRepository(remote: CircuitDataSource.Remote): CircuitRepository {
    return CircuitRepositoryImplement(remote)
}

fun provideRaceRepository(remote: RaceDataSource.Remote): RaceRepository {
    return RaceRepositoryImplement(remote)
}
