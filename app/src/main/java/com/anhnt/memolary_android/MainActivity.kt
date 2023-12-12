package com.anhnt.memolary_android

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.anhnt.memolary_android.databinding.ActivityMainBinding
import com.anhnt.memolary_android.ui.courses.view.CoursesFragment
import com.anhnt.memolary_android.ui.profile.view.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var navView: NavigationView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_view).navigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // init navigation
        navController = findNavController(R.id.nav_host_fragment_content_main)
        bottomNavigationView = binding.bottomNavigationView
        navView = binding.navView
        navView.setupWithNavController(navController)
        setupWithNavController(bottomNavigationView, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment) {
                bottomNavigationView.visibility = View.GONE
            } else {
                bottomNavigationView.visibility = View.VISIBLE
            }
        }
        bottomNavigationView.setOnItemSelectedListener { navItem ->
            when (navItem.itemId) {
                R.id.courses_nav -> {
                    Log.e("Tapped", "Courses")
                    replaceFragment(CoursesFragment.newInstance())
                    true
                }

                R.id.profile_nav -> {
                    Log.e("Tapped", "Profile")
                    replaceFragment(ProfileFragment.newInstance())
                    true
                }

                else -> false
            }
        }
    }


    private fun replaceFragment(newFragment: Fragment) {
        val fragmentManagerTransaction = supportFragmentManager.beginTransaction()
        fragmentManagerTransaction.replace(
            R.id.home_frame_layout,
            newFragment
        )
        fragmentManagerTransaction.commit()
    }

    fun setUpCourses() {
        replaceFragment(CoursesFragment.newInstance())
    }


}