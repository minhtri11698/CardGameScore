package com.example.cardgamescore.score_fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardgamescore.R
import com.example.cardgamescore.base.BaseFragment
import com.example.cardgamescore.databinding.ScoreFragmentLayoutBinding
import com.example.cardgamescore.dialog.AddUserDialog
import com.example.cardgamescore.model.GameSetting
import com.example.cardgamescore.score_fragment.adapter.PlayerListAdapter
import com.example.cardgamescore.ulti.setOnClick

class ScoreFragment: BaseFragment<ScoreFragmentLayoutBinding, ScoreViewModel>(ScoreViewModel::class) {

    private lateinit var playerAdapter: PlayerListAdapter
    private var roundPointChanged = false

    override fun onViewReady() {
        setUpAdapter()
        setClickListener()
    }

    override fun observedData() {
        viewModel.playerList.observe(this) {
            binding.noPlayer.visibility = if (it.isNullOrEmpty()) View.VISIBLE else View.GONE
            playerAdapter.setData(it)
        }
    }

    override fun getLayoutId(): Int = R.layout.score_fragment_layout

    private fun setUpAdapter() {
        playerAdapter = PlayerListAdapter(
            arrayListOf(),
            GameSetting(5)
        )
        binding.scoreRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.scoreRecyclerView.adapter = playerAdapter
        playerAdapter.pointChange = {
            if (!roundPointChanged) {
                roundPointChanged = true
                binding.bottomLayout.visibility = View.VISIBLE
            }
        }
        playerAdapter.onLongClickItem = {
            viewModel.playerList.value?.removeAt(it)
            viewModel.playerList.value?.let { it1 -> playerAdapter.setData(it1) }
        }
    }

    private fun setClickListener() {
        binding.addUser.setOnClick {
            AddUserDialog.show(childFragmentManager).apply {
                onConfirm = { newPlayer ->
                    viewModel.playerList.value?.let {
                        newPlayer.isHost = it.size == 0
                        it.add(newPlayer)
                    } ?: kotlin.run {
                        newPlayer.isHost = true
                        viewModel.playerList.value = arrayListOf(newPlayer)
                    }
                    viewModel.insertPlayer(newPlayer)
                    viewModel.playerList.value = viewModel.playerList.value
                }
            }
        }

        binding.sortByPoint.setOnClick {
            viewModel.playerList.value?.sortByDescending { it.playerPoint }
            viewModel.playerList.value = viewModel.playerList.value
        }

        binding.resetGame.setOnClick {
            viewModel.playerList.value = arrayListOf()
        }

        binding.confirmEndRound.setOnClick {
            playerAdapter.confirmDataChange()
            roundPointChanged = false
            binding.bottomLayout.visibility = View.GONE
        }

        binding.discardRoundChange.setOnClick {
            playerAdapter.discardChange()
            roundPointChanged = false
            binding.bottomLayout.visibility = View.GONE
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