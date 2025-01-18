package com.example.runiquewsrpreparation.auth.data

import android.content.SharedPreferences
import com.example.runiquewsrpreparation.auth.domain.AuthInfo
import com.example.runiquewsrpreparation.auth.domain.SessionStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SessionStorageImpl(
    private val sharedPreferences: SharedPreferences,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : SessionStorage {

    override suspend fun get(): AuthInfo? = withContext(dispatcher) {
        val json = sharedPreferences.getString(KEY_AUTH_INFO, null)
        json?.let {
            Json.decodeFromString<AuthInfo>(it)
        }
    }

    override suspend fun set(info: AuthInfo?) {
        withContext(dispatcher) {
            if (info === null) {
                sharedPreferences.edit().remove(KEY_AUTH_INFO).commit()
                return@withContext
            }

            val json = Json.encodeToString(info)
            sharedPreferences.edit().putString(KEY_AUTH_INFO, json).commit()
        }
    }

    companion object {
        private const val KEY_AUTH_INFO = "KEY_AUTH_INFO"
    }
}