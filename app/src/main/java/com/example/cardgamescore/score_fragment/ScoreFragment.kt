package com.example.cardgamescore.score_fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardgamescore.R
import com.example.cardgamescore.base.BaseFragment
import com.example.cardgamescore.databinding.ScoreFragmentLayoutBinding
import com.example.cardgamescore.model.GameSetting
import com.example.cardgamescore.model.Player
import com.example.cardgamescore.score_fragment.adapter.PlayerListAdapter
import com.example.cardgamescore.ulti.setOnClick

class ScoreFragment: BaseFragment<ScoreFragmentLayoutBinding, ScoreViewModel>(ScoreViewModel::class) {

    private lateinit var playerAdapter: PlayerListAdapter

    override fun onViewReady() {
        playerAdapter = PlayerListAdapter(
            arrayListOf(Player(0, "Tri", 0, true), Player(1, "Tri 2", 0, false)),
            GameSetting(5)
        )
        binding.scoreRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.scoreRecyclerView.adapter = playerAdapter
    }

    override fun observedData() {
//        viewModel.playerList.observe(this) {
//            playerAdapter.setData(it)
//        }
    }

    override fun getLayoutId(): Int = R.layout.score_fragment_layout

    fun setClickListener() {
        binding.addUser.setOnClick {

        }
    }

    companion object {
        fun newInstance(): ScoreFragment {
            val fragment = ScoreFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}