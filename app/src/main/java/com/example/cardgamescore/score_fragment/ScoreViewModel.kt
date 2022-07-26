package com.example.cardgamescore.score_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cardgamescore.base.BaseViewModel
import com.example.cardgamescore.local_data_source.repository.LocalDataRepository
import com.example.cardgamescore.local_data_source.use_case.DeletePlayerUseCase
import com.example.cardgamescore.local_data_source.use_case.GetAllPlayerDataUseCase
import com.example.cardgamescore.local_data_source.use_case.InsertPlayerUseCase
import com.example.cardgamescore.local_data_source.use_case.UpdatePlayerUseCase
import com.example.cardgamescore.model.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ScoreViewModel(
    private val getAllPlayerDataUseCase: GetAllPlayerDataUseCase,
    private val deletePlayerUseCase: DeletePlayerUseCase,
    private val insertPlayerUseCase: InsertPlayerUseCase,
    private val updatePlayerUseCase: UpdatePlayerUseCase
): BaseViewModel() {

    val playerList = MutableLiveData<ArrayList<Player>>(arrayListOf())

    init {
        getPlayer()
    }

    private fun getPlayer() {
        viewModelScope.launch {
            getAllPlayerDataUseCase.execute(Unit).collect {
                handleCallData(it)?.let { playerData ->
                    playerList.value = playerData
                }
            }
        }
    }

    fun deletePlayer(id: Int) {
        viewModelScope.launch {
            deletePlayerUseCase.execute(id).collect {
                handleCallData(it)?.let {
                    getPlayer()
                }
            }
        }
    }

    fun updatePlayer(player: Player) {
        viewModelScope.launch {
            updatePlayerUseCase.execute(player).collect {
                handleCallData(it)?.let {
                }
            }
        }
    }

    fun insertPlayer(player: Player) {
        viewModelScope.launch {
            insertPlayerUseCase.execute(player).collect {
                handleCallData(it)?.let {

                }
            }
        }
    }

}