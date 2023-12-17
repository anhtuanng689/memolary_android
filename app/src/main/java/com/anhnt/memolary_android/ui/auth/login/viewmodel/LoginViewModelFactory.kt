package com.anhnt.memolary_android.ui.auth.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anhnt.memolary_android.data.auth.source.AuthDataSource
import com.anhnt.memolary_android.data.auth.source.AuthRepository
import com.anhnt.memolary_android.data.auth.source.AuthService
import com.anhnt.memolary_android.network.RestClient

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                authRepository = AuthRepository(
                    dataSource = AuthDataSource(
                        authService = RestClient.getApiService(AuthService::class.java)
                    )
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}