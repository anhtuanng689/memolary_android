package com.anhnt.memolary_android.ui.courses.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anhnt.memolary_android.R
import com.anhnt.memolary_android.ui.courses.viewmodel.CoursesViewModel

class CoursesFragment : Fragment() {

    companion object {
        fun newInstance() = CoursesFragment()
    }

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