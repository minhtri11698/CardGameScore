package com.example.cardgamescore.score_fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cardgamescore.R
import com.example.cardgamescore.base.BaseBindingViewHolder
import com.example.cardgamescore.databinding.PlayerScoreItemBinding
import com.example.cardgamescore.model.GameSetting
import com.example.cardgamescore.model.Player
import com.example.cardgamescore.ulti.setOnClick

class PlayerListAdapter constructor(data: ArrayList<Player>, var settingData: GameSetting) :
    RecyclerView.Adapter<PlayerListViewHolder>() {
    private var list = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListViewHolder {
        return PlayerListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.player_score_item, parent, false),
            settingData
        )
    }

    override fun onBindViewHolder(holder: PlayerListViewHolder, position: Int) {
        val item = list[position]
        holder.onAddPoint = {
            val hostIndex = list.indexOfFirst { it.isHost }
            if (hostIndex >= 0) {
                list[hostIndex].playerPoint = list[hostIndex].playerPoint.minus(settingData.pointPerGame)
                notifyItemChanged(hostIndex)
            }
        }
        holder.onMinusPoint = {
            val hostIndex = list.indexOfFirst { it.isHost }
            if (hostIndex >= 0) {
                list[hostIndex].playerPoint = list[hostIndex].playerPoint.plus(settingData.pointPerGame)
                notifyItemChanged(hostIndex)
            }
        }
        holder.onChangeHost = {
            val hostIndex = list.indexOfFirst { it.isHost }
            val host = list[hostIndex]
            if (hostIndex >= 0) {
                host.isHost = false
                notifyItemChanged(hostIndex)
            }
        }
        holder.onBind(item)
    }

    override fun getItemCount(): Int = list.size

    fun setData(data: ArrayList<Player>) {
        list = data
        notifyDataSetChanged()
    }
}

class PlayerListViewHolder(itemView: View, var settingData: GameSetting): BaseBindingViewHolder<Player, PlayerScoreItemBinding>(itemView) {
    var onAddPoint: (() -> (Unit))? = null
    var onMinusPoint: (() -> (Unit))? = null
    var onChangeHost: (() -> (Unit))? = null

    override fun onBind(item: Player) {
        binding.isHost = item.isHost
        binding.playerPoint = item.playerPoint
        binding.playerName.text = item.playerName
        binding.addPoint.setOnClickListener {
            onAddPoint?.invoke()
            item.playerPoint = item.playerPoint.plus(settingData.pointPerGame)
            binding.playerPoint = item.playerPoint
        }
        binding.minusPoint.setOnClickListener {
            onMinusPoint?.invoke()
            item.playerPoint = item.playerPoint.minus(settingData.pointPerGame)
            binding.playerPoint = item.playerPoint
        }

        binding.isHostButton.setOnClickListener {
            if (item.isHost) return@setOnClickListener
            onChangeHost?.invoke()
            item.isHost = true
            binding.isHost = true
        }
    }
}