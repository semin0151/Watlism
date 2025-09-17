package com.semin.watlism.data.di

import com.semin.watlism.data.repository.MovieRepositoryImpl
import com.semin.watlism.data.repository.SeriesRepositoryImpl
import com.semin.watlism.data.repository.TitleRepositoryImpl
import com.semin.watlism.domain.repository.MovieRepository
import com.semin.watlism.domain.repository.SeriesRepository
import com.semin.watlism.domain.repository.TitleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsTitleRepository(
        repositoryImpl: TitleRepositoryImpl
    ): TitleRepository

    @Binds
    @Singleton
    abstract fun bindsMovieRepository(
        repositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @Singleton
    abstract fun bindsSeriesRepository(
        repositoryImpl: SeriesRepositoryImpl
    ): SeriesRepository
}