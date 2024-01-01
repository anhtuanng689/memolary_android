package com.anhnt.memolary_android.ui.home.courses.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anhnt.memolary_android.databinding.FragmentCoursesBinding
import com.anhnt.memolary_android.ui.home.courses.viewmodel.CoursesViewModel
import com.anhnt.memolary_android.ui.home.courses.viewmodel.CoursesViewModelFactory
import kotlinx.coroutines.launch

class CoursesFragment : Fragment() {
    private lateinit var binding: FragmentCoursesBinding

    private lateinit var navController: NavController
    private lateinit var courseViewModel: CoursesViewModel

    private lateinit var myCoursesRecyclerView: RecyclerView

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

        myCoursesRecyclerView = binding.myCoursesRecyclerView


        courseViewModel.myCoursesResult.observe(viewLifecycleOwner, Observer { myCoursesResult ->
            myCoursesResult ?: return@Observer
            myCoursesResult.error?.let {
                showGetFailed(it)
            }
            myCoursesResult.success?.let {
                Log.e("Leu leu", it.toString())
                myCoursesRecyclerView.layoutManager = LinearLayoutManager(activity)
                myCoursesRecyclerView.adapter = MyCoursesAdapter(it)
            }

        })

        lifecycleScope.launch {
            courseViewModel.getMyCourses(
            )
        }
    }

    private fun showGetFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_SHORT).show()
    }


}