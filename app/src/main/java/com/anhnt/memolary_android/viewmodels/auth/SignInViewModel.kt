package com.anhnt.memolary_android.viewmodels.auth

import androidx.lifecycle.ViewModel
import com.anhnt.memolary_android.data.auth.AuthRepository
import com.anhnt.memolary_android.data.auth.SignInRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    fun signIn(signInRequest: SignInRequest) = authRepository.signIn(signInRequest)
}