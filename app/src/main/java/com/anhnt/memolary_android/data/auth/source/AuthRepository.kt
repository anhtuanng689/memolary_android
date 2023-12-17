package com.anhnt.memolary_android.data.auth.source

import com.anhnt.memolary_android.data.Result
import com.anhnt.memolary_android.data.auth.model.LoginResponse
import com.anhnt.memolary_android.network.RestClient
import com.anhnt.memolary_android.util.AppPreferences

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class AuthRepository(val dataSource: AuthDataSource) {


    fun logout() {
        AppPreferences.accessToken = null
        AppPreferences.isLoggedIn = null
        RestClient.clearBearToken()
        dataSource.logout()
    }

    suspend fun login(username: String, password: String): Result<LoginResponse> {
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loginResponse: LoginResponse) {
        RestClient.setBearToken(loginResponse.accessToken)
        AppPreferences.accessToken = loginResponse.accessToken
        AppPreferences.isLoggedIn = true
    }
}