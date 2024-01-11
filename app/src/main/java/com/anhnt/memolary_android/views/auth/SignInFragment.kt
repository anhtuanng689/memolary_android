package com.anhnt.memolary_android.views.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.anhnt.memolary_android.data.auth.SignInRequest
import com.anhnt.memolary_android.databinding.FragmentSignInBinding
import com.anhnt.memolary_android.utils.Resource
import com.anhnt.memolary_android.utils.SessionManager
import com.anhnt.memolary_android.viewmodels.auth.SignInViewModel
import com.anhnt.memolary_android.views.LoadingFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private val viewModel: SignInViewModel by viewModels()

    private lateinit var binding: FragmentSignInBinding
    private lateinit var navController: NavController
    private lateinit var sessionManager: SessionManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        navController = findNavController()
        sessionManager = SessionManager(requireContext())

        binding.signInBtn.isEnabled = true
        binding.signInBtn.setOnClickListener {
            signIn()
        }
        binding.email.setText("test@gmail.com")
        binding.password.setText("123456")
        return binding.root
    }

    private fun signIn() {

        val signInRequest = SignInRequest(
            binding.email.text.trim().toString(),
            binding.password.text.trim().toString()
        )
        lifecycleScope.launch {
            viewModel.signIn(signInRequest).collect {
                when (it.status) {
                    Resource.Status.LOADING -> {

                    }

                    Resource.Status.SUCCESS -> {
                        Timber.i("Log in success with ${it.data}")
                        it.data?.let { data ->
                            sessionManager.token = data.accessToken
//                            sessionManager.userId = data.user.id
                        }
                        Timber.i("Log in success with ${it.data}")
                        navController.navigate(
                            SignInFragmentDirections.actionSignInFragmentToLoadingFragment()
                        )
                        navController.navigate(LoadingFragmentDirections.actionLoadingFragmentToViewPagerFragment())
                    }

                    Resource.Status.ERROR -> {
                        Timber.i("Log in failed")

                    }
                }
            }
        }
    }
}