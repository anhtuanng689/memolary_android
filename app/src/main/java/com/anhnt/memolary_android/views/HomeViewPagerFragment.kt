package com.anhnt.memolary_android.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anhnt.memolary_android.R
import com.anhnt.memolary_android.databinding.FragmentViewPagerBinding
import com.anhnt.memolary_android.views.adapters.COURSES
import com.anhnt.memolary_android.views.adapters.HomePagerAdapter
import com.anhnt.memolary_android.views.adapters.PROFILE
import com.google.android.material.tabs.TabLayoutMediator


class HomeViewPagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = HomePagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            COURSES -> R.drawable.garden_tab_selector
            PROFILE -> R.drawable.plant_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            COURSES -> getString(R.string.courses)
            PROFILE -> getString(R.string.profile)
            else -> null
        }
    }

}