package com.example.cardgamescore.ulti

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.lifecycleScope
import com.example.cardgamescore.R
import com.example.cardgamescore.model.Player
import kotlinx.coroutines.delay

@BindingAdapter("setTextPoint")
fun TextView.setTextPoint(player: Player) {
    val playerPoint = player.playerPoint
    val roundPoint = player.roundPoint
    val textPoint = when {
        roundPoint > 0 -> "$playerPoint +$roundPoint"
        roundPoint < 0 -> "$playerPoint $roundPoint"
        else -> playerPoint.toString()
    }
    text = textPoint
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

@BindingAdapter("hideUnless")
fun View.hideUnless(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.INVISIBLE
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