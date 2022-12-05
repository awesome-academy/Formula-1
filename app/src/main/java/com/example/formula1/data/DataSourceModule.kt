package com.example.formula1.data

import com.example.formula1.data.source.SearchDataSource
import com.example.formula1.data.source.local.SearchDataLocalSource
import com.example.formula1.data.source.remote.SearchDataRemoteSource
import org.koin.dsl.module

val DataSourceModule = module {
    single<SearchDataSource.Remote> { SearchDataRemoteSource(get()) }
    single<SearchDataSource.Local> { SearchDataLocalSource(get()) }
}
