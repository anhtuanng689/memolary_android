package com.anhnt.memolary_android.data.login.source

import com.anhnt.memolary_android.data.Result
import com.anhnt.memolary_android.data.login.model.LoginResponse

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var accessToken: String? = null
        private set

    val isLoggedIn: Boolean
        get() = accessToken != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        accessToken = null
    }

    fun logout() {
        accessToken = null
        dataSource.logout()
    }

    suspend fun login(username: String, password: String): Result<LoginResponse> {
        // handle login
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loginResponse: LoginResponse) {
        this.accessToken = loginResponse.accessToken
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}