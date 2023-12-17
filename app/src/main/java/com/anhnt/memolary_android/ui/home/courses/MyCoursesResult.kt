package com.anhnt.memolary_android.ui.home.courses

import com.anhnt.memolary_android.data.course.model.MyCourse

data class MyCoursesResult(
    val success: List<MyCourse>? = null,
    val error: Int? = null
)