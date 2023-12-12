package com.anhnt.memolary_android.ui.home.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anhnt.memolary_android.data.login.source.LoginDataSource
import com.anhnt.memolary_android.data.login.source.LoginRepository
import com.anhnt.memolary_android.data.login.source.LoginService
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
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource(
                        loginService = RestClient.getApiService(LoginService::class.java)
                    )
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}