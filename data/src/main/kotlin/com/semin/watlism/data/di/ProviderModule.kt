package com.semin.watlism.data.di

import com.semin.watlism.data.provider.DateTimeProviderImpl
import com.semin.watlism.domain.provider.DateTimeProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProviderModule {

    @Binds
    @Singleton
    abstract fun bindsDateTimeProvider(
        dateTimeProviderImpl: DateTimeProviderImpl
    ) : DateTimeProvider
}