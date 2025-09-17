package com.semin.watlism.data.system.di

import com.semin.watlism.data.datasource.system.DateTimeSystemDataSource
import com.semin.watlism.data.system.datasource.DateTimeSystemDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SystemDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsDateTimeSystemDataSource(
        dateTimeSystemDataSourceImpl: DateTimeSystemDataSourceImpl
    ) : DateTimeSystemDataSource
}