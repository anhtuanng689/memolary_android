package com.anhnt.memolary_android.ui.login

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: Token? = null,
    val error: Int? = null
)