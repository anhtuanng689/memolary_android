package com.anhnt.memolary_android.ui.auth.login.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anhnt.memolary_android.R
import com.anhnt.memolary_android.data.Result
import com.anhnt.memolary_android.data.auth.source.AuthRepository
import com.anhnt.memolary_android.ui.auth.login.LoginFormState
import com.anhnt.memolary_android.ui.auth.login.LoginResult
import com.anhnt.memolary_android.ui.auth.login.Token

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    suspend fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = authRepository.login(username, password)

        if (result is Result.Success) {
            _loginResult.postValue(
                LoginResult(success = Token(result.data.accessToken))
            )
        } else {
            _loginResult.postValue(LoginResult(error = R.string.login_failed))
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.postValue(LoginFormState(usernameError = R.string.invalid_username))
        } else if (!isPasswordValid(password)) {
            _loginForm.postValue(LoginFormState(passwordError = R.string.invalid_password))
        } else {
            _loginForm.postValue(LoginFormState(isDataValid = true))
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}