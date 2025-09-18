package com.semin.watlism.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.genresPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "genres")