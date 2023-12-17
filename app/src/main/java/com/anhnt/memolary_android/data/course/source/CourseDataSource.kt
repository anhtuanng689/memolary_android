package com.anhnt.memolary_android.data.course.source

import com.anhnt.memolary_android.data.Result
import com.anhnt.memolary_android.data.course.model.MyCoursesResponse
import com.anhnt.memolary_android.network.ApiService
import retrofit2.http.POST
import java.io.IOException

interface CourseService : ApiService {
    @POST("/courses")
    suspend fun getMyCourses(): MyCoursesResponse
}

class CourseDataSource(private val courseService: CourseService) {

    suspend fun getMyCourses(): Result<MyCoursesResponse> {
        return try {
            val response = courseService.getMyCourses()
            Result.Success(response)
        } catch (e: Throwable) {
            e.printStackTrace()
            Result.Error(IOException("Error getting my courses", e))
        }
    }
}