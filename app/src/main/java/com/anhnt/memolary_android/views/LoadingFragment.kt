package com.anhnt.memolary_android.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anhnt.memolary_android.databinding.FragmentLoadingBinding
import com.anhnt.memolary_android.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoadingFragment : Fragment() {
    private lateinit var binding: FragmentLoadingBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoadingBinding.inflate(inflater, container, false)
        sessionManager = SessionManager(requireContext())




        setUpController()
        return binding.root
    }

    private fun setUpController() {
        //it is the test, remove it latter
//        navController.navigate(
//            //LoadingFragmentDirections.actionLoadingFragmentToSignOptionFragment()
//            LoadingFragmentDirections.actionLoadingFragmentToHomeFragment()
//        )

        // check user signing state through SessionManager
        if (sessionManager.token.isNullOrEmpty()) {
            // user didn't sign in yet (the first time or user sign out)
            // check the data in Room database. if it existed, delete it all
            // call function from LoadingViewModel
            // then navigate it into SignOptionFragment
            findNavController().navigate(
                LoadingFragmentDirections.actionLoadingFragmentToSignInFragment()
            )
        } else {
            // user have just signed in from SignInFragment / SignUpFragment
            // or signed before - the data is already in Room
            // check it and fetch it => call function from LoadingViewModel
            // then navigate it into HomeFragment
            findNavController().navigate(
                LoadingFragmentDirections.actionLoadingFragmentToViewPagerFragment()
            )
        }
    }
}