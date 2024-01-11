package com.anhnt.memolary_android.di

import android.content.Context
import com.anhnt.memolary_android.utils.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    @Named("WithSessionManager")
    fun provideOkHttpClient(context: Context) = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(context))
        .build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory() = GsonConverterFactory.create()
}