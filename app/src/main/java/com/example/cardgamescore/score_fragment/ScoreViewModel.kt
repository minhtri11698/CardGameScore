package com.example.cardgamescore.score_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cardgamescore.base.BaseViewModel
import com.example.cardgamescore.local_data_source.repository.LocalDataRepository
import com.example.cardgamescore.model.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScoreViewModel(
//    private val localDataRepository: LocalDataRepository
): BaseViewModel() {

    val playerList = MutableLiveData<ArrayList<Player>>(arrayListOf())

    init {
//        getPlayer()
    }

//    fun getPlayer() {
//        viewModelScope.launch {
//            playerList.value = localDataRepository.getAllPlayer()
//        }
//    }

}