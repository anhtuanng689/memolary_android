package com.anhnt.memolary_android.viewmodels.courses

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.anhnt.memolary_android.data.courses.CourseRepository
import com.anhnt.memolary_android.data.courses.MyCourse
import com.anhnt.memolary_android.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val courseRepository: CourseRepository
) : ViewModel() {

    fun getMyCourses(): LiveData<Resource<List<MyCourse>>> = courseRepository.getMyCourses().flowOn(
        Dispatchers.IO
    ).catch { Timber.i(it) }
        .asLiveData(viewModelScope.coroutineContext)
}