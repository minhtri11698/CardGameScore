package com.example.cardgamescore.score_fragment

import androidx.lifecycle.MutableLiveData
import com.example.cardgamescore.base.BaseViewModel
import com.example.cardgamescore.model.Player

class ScoreViewModel: BaseViewModel() {

    val playerList = MutableLiveData<ArrayList<Player>>(arrayListOf())

}