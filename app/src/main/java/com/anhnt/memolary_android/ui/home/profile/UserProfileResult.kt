package com.anhnt.memolary_android.ui.home.profile

import com.anhnt.memolary_android.data.user.model.UserProfile

data class UserProfileResult(
    val success: UserProfile? = null,
    val error: Int? = null
)