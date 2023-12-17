package com.anhnt.memolary_android.data.user.source

import com.anhnt.memolary_android.data.Result
import com.anhnt.memolary_android.data.user.model.UserProfileResponse

class UserRepository(val dataSource: UserDataSource) {
    
    suspend fun getProfile(): Result<UserProfileResponse> {
        return dataSource.getProfile()
    }

}