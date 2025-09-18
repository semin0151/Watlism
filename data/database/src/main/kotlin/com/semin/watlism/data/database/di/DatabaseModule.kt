package com.semin.watlism.data.database.di

import android.content.Context
import com.semin.watlism.data.database.GenreDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesGenreDatabase(@ApplicationContext context: Context): GenreDatabase =
        GenreDatabase.getInstance(context)

    @Provides
    fun providesGenreDao(genreDatabase: GenreDatabase) = genreDatabase.getGenreDao()

    @DatabaseDispatcher
    @Provides
    fun providesDatabaseDispatcher() = Dispatchers.IO
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DatabaseDispatcher
