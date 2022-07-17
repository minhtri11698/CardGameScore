package com.example.cardgamescore.ulti

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.lifecycleScope
import com.example.cardgamescore.R
import kotlinx.coroutines.delay

@BindingAdapter("setTextPoint")
fun TextView.setTextPoint(playerPoint: Int) {
    text = playerPoint.toString()
    when {
        playerPoint == 0 -> setTextColor(Color.BLACK)
        playerPoint < 0 -> setTextColor(Color.RED)
        playerPoint > 0 -> setTextColor(Color.GREEN)
    }
}

@BindingAdapter("setIsHostIcon")
fun ImageView.setIsHostIcon(isHost: Boolean) {
    if (isHost) {
        setImageResource(R.drawable.crown_selected)
    } else {
        setImageResource(R.drawable.crown_unselected)
    }
}

@BindingAdapter("app:onSafeClick")
fun View.setOnClick(
    onClickListener: View.OnClickListener
) = setOnClickListener {
    val lifecycleScope = (context as? AppCompatActivity)?.lifecycleScope
    lifecycleScope?.launchWhenStarted {
        if (!isClickable) return@launchWhenStarted
        isClickable = false
        onClickListener.onClick(this@setOnClick)
        delay(500L)
        isClickable = true
    } ?: onClickListener.onClick(this)
}

inline fun View.setOnClick(
    crossinline onClick: () -> Unit
) = setOnClick(View.OnClickListener {
    onClick()
})