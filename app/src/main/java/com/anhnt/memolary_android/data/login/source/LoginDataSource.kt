package com.anhnt.memolary_android.data.login.source

import android.util.Log
import com.anhnt.memolary_android.data.Result
import com.anhnt.memolary_android.data.login.model.LoggedInUser
import com.anhnt.memolary_android.data.login.model.LoginRequest
import com.anhnt.memolary_android.network.ApiService
import com.anhnt.memolary_android.network.RestClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.IOException
import java.util.UUID

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

interface LoginService : ApiService {
    @POST("/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoggedInUser
}

class LoginDataSource (val loginService: LoginService) {

   suspend fun login(username: String, password: String): Result<LoggedInUser> {
       return try {
           val user = loginService.login(LoginRequest(username, password))
           Result.Success(user)
       } catch (e: Throwable) {
           e.printStackTrace()
           Result.Error(IOException("Error logging in", e))
       }
    }

    fun logout() {

        // TODO: revoke authentication
    }
}