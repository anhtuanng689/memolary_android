package com.anhnt.memolary_android.data.login.source

import com.anhnt.memolary_android.data.Result
import com.anhnt.memolary_android.data.login.model.LoginResponse
import com.anhnt.memolary_android.util.AppPreferences

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {
    var accessToken: String? = AppPreferences.accessToken

    var isLoggedIn: Boolean? = AppPreferences.isLoggedIn


    init {

    }

    fun logout() {
        accessToken = null
        isLoggedIn = null
        AppPreferences.accessToken = null
        AppPreferences.isLoggedIn = null
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
        AppPreferences.accessToken = loginResponse.accessToken
        AppPreferences.isLoggedIn = true
    }
}