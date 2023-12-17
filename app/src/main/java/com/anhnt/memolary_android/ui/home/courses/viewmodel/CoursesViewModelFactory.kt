package com.anhnt.memolary_android.ui.home.courses.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anhnt.memolary_android.data.course.source.CourseDataSource
import com.anhnt.memolary_android.data.course.source.CourseRepository
import com.anhnt.memolary_android.data.course.source.CourseService
import com.anhnt.memolary_android.network.RestClient


class CoursesViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoursesViewModel::class.java)) {
            return CoursesViewModel(
                courseRepository = CourseRepository(
                    dataSource = CourseDataSource(
                        courseService = RestClient.getApiService(CourseService::class.java)
                    )
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}