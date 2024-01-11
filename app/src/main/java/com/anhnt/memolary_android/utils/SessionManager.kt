package com.anhnt.memolary_android.utils

import android.content.Context
import android.content.SharedPreferences
import com.anhnt.memolary_android.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import timber.log.Timber

class SessionManager constructor(context: Context) {
    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(
            context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }

    companion object {
        const val USER_TOKEN = "user_token"
//        const val USER_ID = "user_id"
    }

    var token: String?
        get() = prefs.getString(USER_TOKEN, null)
        set(value) = prefs.edit().putString(USER_TOKEN, value).apply()

//    var userId: Int
//        get() = prefs.getInt(USER_ID, 0)
//        set(value) = prefs.edit().putInt(USER_ID, value).apply()

    @ExperimentalCoroutinesApi
    val flow: Flow<String?> = callbackFlow {
        val onTokenChange =
            SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
                when (key) {
                    USER_TOKEN -> this.trySend(sharedPreferences.getString(key, null)).isSuccess
//                    USER_ID -> {}
                    else -> {}
                }
            }

        prefs.registerOnSharedPreferenceChangeListener(onTokenChange)

        awaitClose { prefs.unregisterOnSharedPreferenceChangeListener(onTokenChange) }
    }
        .onStart { emit(this@SessionManager.token) }
        .catch { Timber.i(it) }
        .flowOn(Dispatchers.Default)
}