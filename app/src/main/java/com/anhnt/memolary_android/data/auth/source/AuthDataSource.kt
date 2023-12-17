package com.anhnt.memolary_android.data.auth.source

import com.anhnt.memolary_android.data.Result
import com.anhnt.memolary_android.data.auth.model.LoginRequest
import com.anhnt.memolary_android.data.auth.model.LoginResponse
import com.anhnt.memolary_android.network.ApiService
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

interface AuthService : ApiService {
    @POST("/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}

class AuthDataSource(val authService: AuthService) {

    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return try {
            val response = authService.login(LoginRequest(username, password))
            Result.Success(response)
        } catch (e: Throwable) {
            e.printStackTrace()
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
    }
}