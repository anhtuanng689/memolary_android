package com.anhnt.memolary_android.data.courses

data class MyCoursesResponse(
    val data: List<MyCourse>
)

data class MyCourse(
    val id: String,
    val name: String,
    val image: String,
    val number: Integer,
    val description: String,
    val viewCount: Integer
)
