package com.anhnt.memolary_android.data.user.source

import com.anhnt.memolary_android.data.Result
import com.anhnt.memolary_android.data.user.model.UserProfileResponse
import com.anhnt.memolary_android.network.ApiService
import retrofit2.http.GET
import java.io.IOException


interface UserService : ApiService {
    @GET("/user/profile")
    suspend fun getProfile(): UserProfileResponse
}

class UserDataSource(val userService: UserService) {

    suspend fun getProfile(): Result<UserProfileResponse> {
        return try {
            val response = userService.getProfile()
            Result.Success(response)
        } catch (e: Throwable) {
            e.printStackTrace()
            Result.Error(IOException("Error getting profile", e))
        }
    }
}