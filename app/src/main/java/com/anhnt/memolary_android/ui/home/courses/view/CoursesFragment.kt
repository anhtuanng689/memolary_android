package com.anhnt.memolary_android.ui.home.courses.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anhnt.memolary_android.R
import com.anhnt.memolary_android.ui.home.courses.viewmodel.CoursesViewModel

class CoursesFragment : Fragment() {

    private lateinit var viewModel: CoursesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_courses, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoursesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}