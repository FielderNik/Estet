package com.culture.estet.domain.repository.settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.culture.estet.data.datastore.AppDataStore.appDataStore
import com.culture.estet.data.datastore.AppSettingsScheme
import kotlinx.coroutines.flow.*
import java.io.IOException

interface AppSettingsRepository {
    suspend fun getUserIdFlow(): Flow<String?>
    suspend fun getUserId(): String?
    suspend fun saveUserId(id: String)
}

class AppSettingsRepositoryImpl(private val context: Context) : AppSettingsRepository {

    private val dataStore = context.appDataStore

    override suspend fun getUserIdFlow(): Flow<String?> {
        return dataStore.data
            .catch {
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map {
                it[AppSettingsScheme.USER_ID]
            }
            .distinctUntilChanged()
            .cancellable()

    }

    override suspend fun getUserId(): String? {
        return getUserIdFlow().first()
    }

    override suspend fun saveUserId(id: String) {
        dataStore.edit { preferences ->
            preferences[AppSettingsScheme.USER_ID] = id
        }
    }

}
