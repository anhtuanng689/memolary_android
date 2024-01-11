package com.anhnt.memolary_android.views.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anhnt.memolary_android.R
import com.anhnt.memolary_android.viewmodels.courses.CourseDetailViewModel

class CourseDetailFragment : Fragment() {

    companion object {
        fun newInstance() = CourseDetailFragment()
    }

    private lateinit var viewModel: CourseDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CourseDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}