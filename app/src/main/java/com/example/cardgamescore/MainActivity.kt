package com.example.cardgamescore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.cardgamescore.di.viewModelModule
import com.example.cardgamescore.splash_fragment.SplashFragment
import kotlinx.coroutines.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainActivity)
            modules(viewModelModule)
        }
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