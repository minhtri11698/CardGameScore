package com.example.cardgamescore.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseBindingViewHolder<T, BINDING : ViewDataBinding>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    val binding: BINDING = DataBindingUtil.bind(itemView)!!

    abstract fun onBind(item: T)
}