package com.anhnt.memolary_android.network

import com.anhnt.memolary_android.util.AppPreferences
import com.anhnt.memolary_android.util.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    private const val baseUrl = "http://${Constants.BASE_URL}:3000" // Replace with your API URL

    private var bearToken: String? = null

    init {
        bearToken = AppPreferences.accessToken
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor { chain ->
            val originalRequest = chain.request()

            if (bearToken != null) {
                val modifiedRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $bearToken")
                    .build()
                chain.proceed(modifiedRequest)
            } else {
                chain.proceed(originalRequest)
            }
        }
        .build()

    fun setBearToken(token: String?) {
        bearToken = token
    }

    fun clearBearToken() {
        bearToken = null
    }

    private val gson = GsonBuilder().setLenient().create()


    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    fun <T : ApiService> getApiService(apiService: Class<T>): T {
        return retrofit.create(apiService)
    }
}
