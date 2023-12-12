package com.anhnt.memolary_android.ui.home.profile.viewmodel

import androidx.lifecycle.ViewModel
import com.anhnt.memolary_android.data.login.source.LoginRepository

class ProfileViewModel(private val loginRepository: LoginRepository) : ViewModel() {


    fun logout() {
        loginRepository.logout()
    }

}