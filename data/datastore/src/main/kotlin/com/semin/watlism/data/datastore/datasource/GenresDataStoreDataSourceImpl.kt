package com.semin.watlism.data.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import javax.inject.Inject

class GenresDataStoreDataSourceImpl @Inject constructor(
    private val preferenceDataStore: DataStore<Preferences>
)