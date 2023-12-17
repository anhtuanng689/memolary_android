package com.anhnt.memolary_android.ui.home.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anhnt.memolary_android.R
import com.anhnt.memolary_android.data.Result
import com.anhnt.memolary_android.data.auth.source.AuthRepository
import com.anhnt.memolary_android.data.user.model.UserProfile
import com.anhnt.memolary_android.data.user.source.UserRepository
import com.anhnt.memolary_android.ui.home.profile.UserProfileResult
import java.util.Date

class ProfileViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userProfileResult = MutableLiveData<UserProfileResult>()
    val userProfileResult: LiveData<UserProfileResult> = _userProfileResult


    suspend fun getProfile() {
        val result = userRepository.getProfile()
        if (result is Result.Success) {
            val userProfile = result.data.data
            _userProfileResult.postValue(
                UserProfileResult(
                    success = UserProfile(
                        name = userProfile.name,
                        email = userProfile.email,
                        avatar = userProfile.avatar,
                        fcmToken = userProfile.fcmToken,
                        sex = userProfile.sex,
                        phone = userProfile.phone,
                        // TODO
                        birthday = Date(),
                        recallWords = userProfile.recallWords,
                        recallTime = userProfile.recallTime,
                    )
                )
            )

        } else {
            _userProfileResult.postValue(UserProfileResult(error = R.string.login_failed))
        }
    }

    fun logout() {
        authRepository.logout()
    }

}