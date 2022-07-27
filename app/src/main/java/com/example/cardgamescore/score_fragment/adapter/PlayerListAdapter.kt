package com.example.cardgamescore.score_fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.cardgamescore.R
import com.example.cardgamescore.databinding.PlayerScoreItemBinding
import com.example.cardgamescore.model.GameSetting
import com.example.cardgamescore.model.Player

class PlayerListAdapter constructor(data: ArrayList<Player>, var settingData: GameSetting) :
    RecyclerView.Adapter<PlayerListViewHolder>() {
    private var list = data
    var pointChange: (() -> (Unit))? = null
    var onLongClickItem: ((Int) -> (Unit))? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListViewHolder {
        return PlayerListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.player_score_item, parent, false),
            settingData)
    }

    override fun onBindViewHolder(holder: PlayerListViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.setOnLongClickListener {
            onLongClickItem?.invoke(position)
            return@setOnLongClickListener true
        }
        holder.onAddPoint = {
            val hostIndex = list.indexOfFirst { it.isHost }
            if (hostIndex >= 0) {
                list[hostIndex].roundPoint =
                    list[hostIndex].roundPoint.minus(settingData.pointPerGame)
                notifyItemChanged(hostIndex)
            }
            pointChange?.invoke()
        }
        holder.onMinusPoint = {
            val hostIndex = list.indexOfFirst { it.isHost }
            if (hostIndex >= 0) {
                list[hostIndex].roundPoint =
                    list[hostIndex].roundPoint.plus(settingData.pointPerGame)
                notifyItemChanged(hostIndex)
            }
            pointChange?.invoke()
        }
        holder.onChangeHost = {
            val hostIndex = list.indexOfFirst { it.isHost }
            val host = list[hostIndex]
            if (hostIndex >= 0) {
                host.isHost = false
                notifyItemChanged(hostIndex)
            }

        }
        holder.onBind(item, list)
    }

    fun confirmDataChange() {
        list.forEach {
            it.playerPoint = it.playerPoint.plus(it.roundPoint)
            it.roundPoint = 0
        }
        notifyDataSetChanged()
    }

    fun discardChange() {
        list.forEach {
            it.roundPoint = 0
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    fun setData(data: ArrayList<Player>) {
        list = data
        notifyDataSetChanged()
    }
}

class PlayerListViewHolder(
    itemView: View,
    var settingData: GameSetting
) : PlayerBindingViewHolder<Player, PlayerScoreItemBinding>(itemView) {
    var onAddPoint: (() -> (Unit))? = null
    var onMinusPoint: (() -> (Unit))? = null
    var onChangeHost: (() -> (Unit))? = null

    override fun onBind(item: Player, data: ArrayList<Player>) {
        binding.isHost = item.isHost
        binding.player = item
        binding.playerName.text = item.playerName
        binding.addPoint.setOnClickListener {
            onAddPoint?.invoke()
            item.roundPoint = item.roundPoint.plus(settingData.pointPerGame)
            binding.player = item
        }
        binding.minusPoint.setOnClickListener {
            onMinusPoint?.invoke()
            item.roundPoint = item.roundPoint.minus(settingData.pointPerGame)
            binding.player = item
        }

        binding.isHostButton.setOnClickListener {
            val check = data.firstOrNull { it.roundPoint != 0 } != null
            if (item.isHost || check) return@setOnClickListener
            onChangeHost?.invoke()
            item.isHost = true
            binding.isHost = true
        }
    }
}

abstract class PlayerBindingViewHolder<T, BINDING : ViewDataBinding>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    val binding: BINDING = DataBindingUtil.bind(itemView)!!

    abstract fun onBind(item: T, data: ArrayList<Player>)
}