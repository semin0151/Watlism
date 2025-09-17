package com.semin.watlism.data.api.di

import com.semin.watlism.data.api.datasource.DiscoverApiDataSourceImpl
import com.semin.watlism.data.api.datasource.GenresApiDataSourceImpl
import com.semin.watlism.data.api.datasource.TrendingApiDataSourceImpl
import com.semin.watlism.data.datasource.api.DiscoverApiDataSource
import com.semin.watlism.data.datasource.api.GenresApiDataSource
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

    @Binds
    @Singleton
    abstract fun bindsDiscoverApiDataSource(
        dataSourceImpl: DiscoverApiDataSourceImpl
    ): DiscoverApiDataSource

    @Binds
    @Singleton
    abstract fun bindsGenresApiDataSource(
        dataSourceImpl: GenresApiDataSourceImpl
    ): GenresApiDataSource
}