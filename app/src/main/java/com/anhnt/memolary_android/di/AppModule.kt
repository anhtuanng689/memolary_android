package com.anhnt.memolary_android.di

import android.content.Context
import com.anhnt.memolary_android.data.auth.AuthService
import com.anhnt.memolary_android.data.courses.CourseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {
    @Provides
    @Singleton
    fun provideAuthService(@ApplicationContext context: Context): AuthService {
        return AuthService.create(context)
    }

    @Provides
    @Singleton
    fun provideCourseService(@ApplicationContext context: Context): CourseService {
        return CourseService.create(context)
    }


}