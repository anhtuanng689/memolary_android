package com.anhnt.memolary_android.data.auth

import com.anhnt.memolary_android.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class AuthRepository @Inject constructor(private val authService: AuthService) {

    fun signIn(signInRequest: SignInRequest): Flow<Resource<SignInResponse>> {
        return flow {
            emit(Resource.loading(null))
            val response = authService.signIn(signInRequest)

            if (!response.isSuccessful || response.code() == 401) {
                emit(Resource.error(null, "Wrong credential..."))
            } else {
                emit(Resource.success(response.body()!!))
            }
        }
    }

    companion object {
        @Volatile
        private var instance: AuthRepository? = null

        fun getInstance(authService: AuthService) =
            instance ?: synchronized(this) {
                instance ?: AuthRepository(authService).also { instance = it }
            }
    }
}