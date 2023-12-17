package com.anhnt.memolary_android.data.user.model

import java.util.Date

data class UserProfileResponse(
    val data: UserProfile
)

data class UserProfile(
    val email: String,
    val name: String,
    val avatar: String,
    val fcmToken: String,
    val sex: String,
    val phone: String,
    val birthday: Date,
    val recallWords: Int,
    val recallTime: String,
)
