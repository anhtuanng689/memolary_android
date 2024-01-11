package com.anhnt.memolary_android.data.courses

import com.anhnt.memolary_android.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CourseRepository @Inject constructor(private val courseService: CourseService) {

    fun getMyCourses(): Flow<Resource<List<MyCourse>>> {
        return flow {
            emit(Resource.loading(null))
            val response = courseService.getMyCourses()

            if (!response.isSuccessful || response.code() == 401) {
                emit(Resource.error(null, "Wrong credential..."))
            } else {
                emit(Resource.success(response.body()!!.data))
            }
        }
    }

    companion object {
        @Volatile
        private var instance: CourseRepository? = null

        fun getInstance(courseService: CourseService) =
            instance ?: synchronized(this) {
                instance ?: CourseRepository(courseService).also { instance = it }
            }
    }
}