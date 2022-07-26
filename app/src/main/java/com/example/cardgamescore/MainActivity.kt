package com.example.cardgamescore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.cardgamescore.splash_fragment.SplashFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onViewReady()
    }


    private fun onViewReady() {
        showFragment(SplashFragment.newInstance(), false, R.id.container)
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean, layoutId: Int) {
        val transaction = supportFragmentManager.beginTransaction().replace(layoutId, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        if (addToBackStack) transaction.addToBackStack(fragment.javaClass.simpleName)
        transaction.commitAllowingStateLoss()
    }

    fun showFragment(fragment: Fragment, addToBackStack: Boolean, containerId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
            .add(containerId, fragment, fragment.javaClass.simpleName)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        if (addToBackStack) transaction.addToBackStack(fragment.javaClass.simpleName)
        transaction.commitAllowingStateLoss()
    }
}