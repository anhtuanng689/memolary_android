package com.anhnt.memolary_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.anhnt.memolary_android.ui.login.view.LoginFragment

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager = supportFragmentManager

        val transaction = fragmentManager.beginTransaction()
        val loginFragment = LoginFragment()
        transaction.add(R.id.fragment_container, loginFragment)
        transaction.commit()
    }

    fun switchFragment(newFragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}