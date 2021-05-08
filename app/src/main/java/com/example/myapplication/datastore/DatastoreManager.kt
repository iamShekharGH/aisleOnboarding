package com.example.myapplication.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatastoreManager @Inject constructor(
    c: Application
) {

    private object PreferenceKeys {
        val TOKEN = stringPreferencesKey("token")
    }

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "com.example.myapplication.datastore")
    private val mDatastore = c.datastore
    val prefFlow = mDatastore.data
        .catch { e ->
            if (e is IOException) {
                e.printStackTrace()
                emit(emptyPreferences())
            } else throw e
        }.map { prefs ->
            val t = prefs[PreferenceKeys.TOKEN] ?: ""

            PreferenceInformation(token = t)
        }

    suspend fun updateTokenInfo(token: String) {
        mDatastore.edit { prefs ->
            prefs[PreferenceKeys.TOKEN] = token
        }
    }
}

data class PreferenceInformation(
    val token: String
)