package com.anhnt.memolary_android.ui.home.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anhnt.memolary_android.data.auth.source.AuthDataSource
import com.anhnt.memolary_android.data.auth.source.AuthRepository
import com.anhnt.memolary_android.data.auth.source.AuthService
import com.anhnt.memolary_android.data.user.source.UserDataSource
import com.anhnt.memolary_android.data.user.source.UserRepository
import com.anhnt.memolary_android.data.user.source.UserService
import com.anhnt.memolary_android.network.RestClient

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ProfileViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(
                authRepository = AuthRepository(
                    dataSource = AuthDataSource(
                        authService = RestClient.getApiService(AuthService::class.java)
                    )
                ),
                userRepository = UserRepository(
                    dataSource = UserDataSource(
                        userService = RestClient.getApiService(
                            UserService::class.java
                        )
                    )
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}