package com.anhnt.memolary_android.views.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.anhnt.memolary_android.views.PlantListFragment
import com.anhnt.memolary_android.views.courses.CoursesFragment

const val COURSES = 0
const val PROFILE = 1

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        COURSES to { CoursesFragment() },
        PROFILE to { PlantListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}