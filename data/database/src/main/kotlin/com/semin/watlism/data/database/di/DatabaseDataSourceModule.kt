package com.semin.watlism.data.database.di

import com.semin.watlism.data.database.datasource.GenresDatabaseDataSourceImpl
import com.semin.watlism.data.datasource.database.GenresDatabaseDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsGenresDatabaseDataSource(
        genresDatabaseDataSourceImpl: GenresDatabaseDataSourceImpl
    ): GenresDatabaseDataSource
}