package com.anhnt.memolary_android.data.login.source

import com.anhnt.memolary_android.data.Result
import com.anhnt.memolary_android.data.login.model.LoginRequest
import com.anhnt.memolary_android.data.login.model.LoginResponse
import com.anhnt.memolary_android.network.ApiService
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

interface LoginService : ApiService {
    @POST("/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}

class LoginDataSource(val loginService: LoginService) {

    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return try {
            val user = loginService.login(LoginRequest(username, password))
            Result.Success(user)
        } catch (e: Throwable) {
            e.printStackTrace()
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
    }
}