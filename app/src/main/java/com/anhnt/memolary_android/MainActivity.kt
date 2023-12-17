package com.anhnt.memolary_android

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.anhnt.memolary_android.databinding.ActivityMainBinding
import com.anhnt.memolary_android.ui.home.courses.view.CoursesFragment
import com.anhnt.memolary_android.ui.home.profile.view.ProfileFragment
import com.anhnt.memolary_android.util.AppPreferences
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
        AppPreferences.setup(applicationContext)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // init navigation
        navController = findNavController(R.id.nav_host_fragment_content_main)
        bottomNavigationView = binding.bottomNavigationView
        navView = binding.navView
        navView.setupWithNavController(navController)
        setupWithNavController(bottomNavigationView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            Log.d("onDestinationChanged", destination.toString())
            if (destination.id != R.id.loginFragment) {
                bottomNavigationView.visibility = View.VISIBLE
            } else {
                bottomNavigationView.visibility = View.GONE
            }
        }
        bottomNavigationView.setOnItemSelectedListener { navItem ->
            when (navItem.itemId) {
                R.id.courses_nav -> {
                    Log.e("Tapped", "Courses")
                    replaceFragment(CoursesFragment())
                    true
                }

                R.id.profile_nav -> {
                    Log.e("Tapped", "Profile")
                    replaceFragment(ProfileFragment())
                    true
                }

                else -> false
            }
        }


    }

    init {
        lifecycleScope.launchWhenResumed {
            if (AppPreferences.isLoggedIn == true) {
                navController.navigate(R.id.homeFragment)
            } else {
                navController.navigate(R.id.loginFragment)
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
        replaceFragment(CoursesFragment())
    }

    private fun resetBottomNavSelectedItemId() {
        bottomNavigationView.selectedItemId = R.id.courses_nav
    }

    fun logout() {
        resetBottomNavSelectedItemId()
        navController.navigate(R.id.loginFragment)
    }


}