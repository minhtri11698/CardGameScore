package com.example.cardgamescore.splash_fragment

import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.DecelerateInterpolator
import com.example.cardgamescore.MainActivity
import com.example.cardgamescore.R
import com.example.cardgamescore.base.BaseFragment
import com.example.cardgamescore.databinding.SplashFragmentBinding
import com.example.cardgamescore.score_fragment.ScoreFragment
import kotlinx.coroutines.*

class SplashFragment: BaseFragment<SplashFragmentBinding, SplashViewModel>(SplashViewModel::class) {
    override fun getLayoutId(): Int = R.layout.splash_fragment

    override fun onViewReady() {
        animateSplash()
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            withContext(Dispatchers.Main) {
                (activity as? MainActivity)?.replaceFragment(ScoreFragment.newInstance(), true, R.id.container)
            }
        }
    }

    override fun observedData() {
    }

    private fun animateSplash() {
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = DecelerateInterpolator()
        fadeIn.duration = 2000

        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator()
        fadeOut.startOffset = 1000
        fadeOut.duration = 2000

        binding.logo.startAnimation(fadeIn)
        binding.notice.startAnimation(fadeIn)
    }

    companion object {
        fun newInstance() : SplashFragment {
            val fragment = SplashFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}