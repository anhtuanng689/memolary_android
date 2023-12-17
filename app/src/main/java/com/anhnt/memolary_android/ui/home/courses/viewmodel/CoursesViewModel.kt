package com.anhnt.memolary_android.ui.home.courses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anhnt.memolary_android.R
import com.anhnt.memolary_android.data.Result
import com.anhnt.memolary_android.data.course.source.CourseRepository
import com.anhnt.memolary_android.ui.home.courses.MyCoursesResult

class CoursesViewModel(
    private val courseRepository: CourseRepository
) : ViewModel() {

    private val _myCoursesResult = MutableLiveData<MyCoursesResult>()
    val myCoursesResult: LiveData<MyCoursesResult> = _myCoursesResult


    suspend fun getMyCourses() {
        val result = courseRepository.getMyCourses()
        if (result is Result.Success) {


        } else {
            _myCoursesResult.postValue(MyCoursesResult(error = R.string.login_failed))
        }
    }

}