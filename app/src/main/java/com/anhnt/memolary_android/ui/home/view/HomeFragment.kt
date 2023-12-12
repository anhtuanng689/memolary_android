package com.anhnt.memolary_android.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.anhnt.memolary_android.MainActivity
import com.anhnt.memolary_android.databinding.FragmentHomeBinding
import com.anhnt.memolary_android.ui.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        navController = findNavController()
        return binding.root
    }

    override fun onStart() {
        val mainActivity: MainActivity = activity as MainActivity
        mainActivity.setUpCourses()
        super.onStart()
    }

}