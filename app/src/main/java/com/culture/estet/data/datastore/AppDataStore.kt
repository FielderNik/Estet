package com.culture.estet.data.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

object AppDataStore {
    private const val DATA_STORE_NAME = "app_preferences"

    val Context.appDataStore by preferencesDataStore(
        name = DATA_STORE_NAME
    )

}