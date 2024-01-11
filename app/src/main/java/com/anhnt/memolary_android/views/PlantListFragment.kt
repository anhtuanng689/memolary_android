package com.anhnt.memolary_android.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anhnt.memolary_android.databinding.FragmentPlantListBinding
import com.anhnt.memolary_android.utils.SessionManager

/**
 * A simple [Fragment] subclass.
 * Use the [PlantListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlantListFragment : Fragment() {
//    private val viewModel: PlantListViewModel by viewModels()

    private lateinit var binding: FragmentPlantListBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantListBinding.inflate(inflater, container, false)
        sessionManager = SessionManager(requireContext())

//        val adapter = PlantAdapter()
//        adapter.onPlantClicked = { plant ->
//            val bundle = bundleOf("plantId" to plant.plantId)
//            requireActivity()
//                .supportFragmentManager
//                .setFragmentResult("plantDetailRequestKey", bundle)
//        }
//        binding.plantList.adapter = adapter
//        subscribeUi(adapter)
//
        setHasOptionsMenu(true)
        binding.signOut.setOnClickListener {
            sessionManager.token = null
            findNavController().navigate(HomeViewPagerFragmentDirections.actionViewPagerFragmentToLoadingFragment())
            findNavController().navigate(LoadingFragmentDirections.actionLoadingFragmentToSignInFragment())
        }


        return binding.root
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_plant_list, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.filter_zone -> {
//                updateData()
//                true
//            }
//            else ->
//                super.onOptionsItemSelected(item)
//        }
//    }

//    private fun subscribeUi(adapter: PlantAdapter) {
//        viewModel.plants.observe(viewLifecycleOwner) { plants ->
//            adapter.submitList(plants)
//        }
//    }

    private fun updateData() {
//        with(viewModel) {
//            if (isFiltered()) {
//                clearGrowZoneNumber()
//            } else {
//                setGrowZoneNumber(9)
//            }
//        }
    }
}