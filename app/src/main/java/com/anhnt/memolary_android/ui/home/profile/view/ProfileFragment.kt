package com.anhnt.memolary_android.ui.home.profile.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.anhnt.memolary_android.MainActivity
import com.anhnt.memolary_android.databinding.FragmentProfileBinding
import com.anhnt.memolary_android.ui.home.profile.viewmodel.ProfileViewModel
import com.anhnt.memolary_android.ui.home.profile.viewmodel.ProfileViewModelFactory

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var navController: NavController

    private lateinit var profileViewModel: ProfileViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("onViewCreated", "onViewCreated Profile")
        super.onViewCreated(view, savedInstanceState)
        profileViewModel = ViewModelProvider(
            this,
            ProfileViewModelFactory()
        )[ProfileViewModel::class.java]

        binding.logoutBtn.setOnClickListener {
            Log.d("Logout", "Logged out")
            profileViewModel.logout()
            val mainActivity: MainActivity = activity as MainActivity
            mainActivity.logout()
        }
    }

    fun logout() {
        Log.d("Logout", "Logged out")

    }

    override fun onStart() {
        super.onStart()
    }

}