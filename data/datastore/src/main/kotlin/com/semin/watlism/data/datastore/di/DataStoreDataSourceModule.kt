package com.semin.watlism.data.datastore.di

import com.semin.watlism.data.datasource.datastore.GenresDataStoreDataSource
import com.semin.watlism.data.datastore.datasource.GenresDataStoreDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsGenresDataStoreDataSource(
        genresDataStoreDataSourceImpl: GenresDataStoreDataSourceImpl
    ): GenresDataStoreDataSource
}