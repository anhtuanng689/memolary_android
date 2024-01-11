package com.anhnt.memolary_android.data.auth

import android.content.Context
import com.anhnt.memolary_android.utils.AuthInterceptor
import com.anhnt.memolary_android.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/login")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): Response<SignInResponse>

    companion object {
//        fun create(context: Context): AuthService {
//            val okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                .addInterceptor(AuthInterceptor(context))
//                .build()
//
//            return Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build()
//                .create(AuthService::class.java)
//        }

        fun create(context: Context): AuthService {

            val okHttpClient =
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(AuthInterceptor(context))
                    .build()


            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(AuthService::class.java)
        }
    }
}
