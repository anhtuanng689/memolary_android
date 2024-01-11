package com.anhnt.memolary_android.views.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anhnt.memolary_android.data.courses.MyCourse
import com.anhnt.memolary_android.databinding.FragmentCoursesBinding
import com.anhnt.memolary_android.utils.Resource
import com.anhnt.memolary_android.viewmodels.courses.CoursesViewModel
import com.anhnt.memolary_android.views.HomeViewPagerFragmentDirections
import com.anhnt.memolary_android.views.adapters.MyCoursesAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CoursesFragment : Fragment() {
    private val viewModel: CoursesViewModel by viewModels()

    private lateinit var binding: FragmentCoursesBinding
    private lateinit var navController: NavController

    private var _myCourse: MutableLiveData<MutableList<MyCourse>> = MutableLiveData()


//    private val viewModel: GardenPlantingListViewModel by viewModels()

    init {
        lifecycleScope.launchWhenResumed {
            refreshMyCourses()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoursesBinding.inflate(inflater, container, false)
        navController = findNavController()

//        val adapter = GardenPlantingAdapter()
//        adapter.onPlantClicked = {
//            val bundle = bundleOf("plantId" to it)
//            requireActivity()
//                .supportFragmentManager
//                .setFragmentResult("plantDetailRequestKey", bundle)
//
//        }
//        binding.gardenList.adapter = adapter
//
//        binding.addPlant.setOnClickListener {
//            navigateToPlantListPage()
//        }
//
//        subscribeUi(adapter, binding)
        setUpUi {
            navController.navigate(HomeViewPagerFragmentDirections.actionViewPagerFragmentToCourseDetailFragment())
        }
        return binding.root
    }

//    private fun subscribeUi(adapter: GardenPlantingAdapter, binding: FragmentGardenBinding) {
//        viewModel.plantAndGardenPlantings.observe(viewLifecycleOwner) { result ->
//            binding.hasPlantings = result.isNotEmpty()
//            adapter.submitList(result) {
//                // At this point, the content should be drawn
//                activity?.reportFullyDrawn()
//            }
//        }
//    }

    private fun setUpUi(listener: (MyCourse) -> Unit = {}) {
        val recyclerView = binding.myCoursesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)

        _myCourse.observe(viewLifecycleOwner) {
            recyclerView.adapter = MyCoursesAdapter(
                it,
                listener
            )
        }
    }

    private fun refreshMyCourses() {
        viewModel.getMyCourses()
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Resource.Status.SUCCESS, Resource.Status.LOADING -> {
                        if (it.data == null) {
                            return@observe
                        }

                        _myCourse.value = it.data as MutableList<MyCourse>?
                    }

                    Resource.Status.ERROR -> {
                        Toast.makeText(
                            context,
                            "Can not load my courses",
                            Toast.LENGTH_LONG
                        ).show()
                        Timber.i(it.message)
                    }

                }
            }
    }

    // TODO: convert to data binding if applicable
    private fun navigateToPlantListPage() {
//        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
//            PLANT_LIST_PAGE_INDEX
    }
}