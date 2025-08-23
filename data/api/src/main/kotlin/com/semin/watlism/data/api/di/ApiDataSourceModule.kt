package com.semin.watlism.data.api.di

import com.semin.watlism.data.api.datasource.TrendingApiDataSourceImpl
import com.semin.watlism.data.datasource.api.TrendingApiDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsTrendingApiDataSource(
        dataSourceImpl: TrendingApiDataSourceImpl
    ): TrendingApiDataSource
}