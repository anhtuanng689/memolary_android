package com.anhnt.memolary_android.ui.home.profile.view

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
import com.anhnt.memolary_android.MainActivity
import com.anhnt.memolary_android.databinding.FragmentProfileBinding
import com.anhnt.memolary_android.ui.home.profile.viewmodel.ProfileViewModel
import com.anhnt.memolary_android.ui.home.profile.viewmodel.ProfileViewModelFactory
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private lateinit var navController: NavController
    private lateinit var profileViewModel: ProfileViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        profileViewModel = ViewModelProvider(
            this,
            ProfileViewModelFactory()
        )[ProfileViewModel::class.java]

        profileViewModel.userProfileResult.observe(viewLifecycleOwner,
            Observer { userProfileResult ->
                userProfileResult ?: return@Observer
//                loadingProgressBar.visibility = View.GONE
                userProfileResult.error?.let {
                    showGetFailed(it)
                }
                userProfileResult.success?.let {
                    Log.e("Name", "Name is $it.name")
                    binding.name.text = it.name
                }
            })

        binding.logoutBtn.setOnClickListener {
            profileViewModel.logout()
            val mainActivity: MainActivity = activity as MainActivity
            mainActivity.logout()
        }
        lifecycleScope.launch {
            profileViewModel.getProfile(
            )
        }
    }

    private fun showGetFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_SHORT).show()
    }

}