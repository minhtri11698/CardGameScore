package com.example.cardgamescore.score_fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
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

        }
        holder.onMinusPoint = {

        }
        holder.onChangeHost = {
            val index = list.indexOfFirst { it.isHost }
            if (index >= 0) {
                list[index].isHost = false
                notifyItemChanged(index)
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
        binding.addPoint.setOnClick {
            onAddPoint?.invoke()
            item.playerPoint = item.playerPoint.plus(settingData.pointPerGame)
            binding.playerPoint = item.playerPoint
        }
        binding.minusPoint.setOnClick {
            onMinusPoint?.invoke()
            item.playerPoint = item.playerPoint.minus(settingData.pointPerGame)
            binding.playerPoint = item.playerPoint
        }

        binding.isHostButton.setOnClick {
            if (item.isHost) return@setOnClick
            onChangeHost?.invoke()
            item.isHost = true
            binding.isHost = true
        }
    }
}