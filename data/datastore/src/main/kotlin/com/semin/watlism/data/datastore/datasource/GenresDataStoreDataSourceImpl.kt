package com.semin.watlism.data.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.semin.watlism.data.datasource.datastore.GenresDataStoreDataSource
import com.semin.watlism.data.datastore.di.GenresPreferencesDataStore
import javax.inject.Inject

class GenresDataStoreDataSourceImpl @Inject constructor(
    @GenresPreferencesDataStore private val preferenceDataStore: DataStore<Preferences>
): GenresDataStoreDataSource