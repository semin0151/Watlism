package com.semin.watlism.data.datastore.di

import android.content.Context
import com.semin.watlism.data.datastore.genresPreferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @GenresPreferencesDataStore
    @Provides
    @Singleton
    fun providesGenresPreferencesDataStore(
        @ApplicationContext context: Context,
    ) = context.genresPreferencesDataStore
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GenresPreferencesDataStore