package com.anhnt.memolary_android.ui.home.courses.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.anhnt.memolary_android.databinding.FragmentCoursesBinding
import com.anhnt.memolary_android.ui.home.courses.viewmodel.CoursesViewModel
import com.anhnt.memolary_android.ui.home.courses.viewmodel.CoursesViewModelFactory

class CoursesFragment : Fragment() {
    private lateinit var binding: FragmentCoursesBinding

    private lateinit var navController: NavController
    private lateinit var courseViewModel: CoursesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoursesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        courseViewModel = ViewModelProvider(
            this,
            CoursesViewModelFactory()
        )[CoursesViewModel::class.java]
    }


}