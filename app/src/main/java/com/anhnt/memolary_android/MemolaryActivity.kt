package com.anhnt.memolary_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.anhnt.memolary_android.databinding.ActivityMemolaryBinding
import com.anhnt.memolary_android.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MemolaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemolaryBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    private val sessionManager by lazy { SessionManager(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        binding = ActivityMemolaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController


        //custom toolbar for destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loadingFragment || destination.id == R.id.signInFragment
            ) {
                supportActionBar?.hide()
            } else {
                supportActionBar?.show()
            }
        }
    }
}