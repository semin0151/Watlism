package com.semin.watlism.data.api.di

import com.semin.watlism.data.api.TmdbDiscoverApi
import com.semin.watlism.data.api.TmdbGenresApi
import com.semin.watlism.data.api.TmdbTrendingApi
import com.semin.watlism.data.api.config.ApiConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @TmdbRetrofit
    @Provides
    @Singleton
    fun provideTmdbRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConfig.TMDB_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideTmdbTrendingApi(
        @TmdbRetrofit retrofit: Retrofit
    ): TmdbTrendingApi {
        return retrofit.create<TmdbTrendingApi>()
    }

    @Provides
    @Singleton
    fun provideTmdbDiscoverApi(
        @TmdbRetrofit retrofit: Retrofit
    ): TmdbDiscoverApi {
        return retrofit.create<TmdbDiscoverApi>()
    }

    @Provides
    @Singleton
    fun provideTmdbGenresApi(
        @TmdbRetrofit retrofit: Retrofit
    ): TmdbGenresApi {
        return retrofit.create<TmdbGenresApi>()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TmdbRetrofit