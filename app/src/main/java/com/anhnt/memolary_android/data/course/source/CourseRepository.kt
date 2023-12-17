package com.anhnt.memolary_android.data.course.source

import com.anhnt.memolary_android.data.Result
import com.anhnt.memolary_android.data.course.model.MyCoursesResponse

class CourseRepository(private val dataSource: CourseDataSource) {
    
    suspend fun getMyCourses(): Result<MyCoursesResponse> {
        return dataSource.getMyCourses()
    }

}