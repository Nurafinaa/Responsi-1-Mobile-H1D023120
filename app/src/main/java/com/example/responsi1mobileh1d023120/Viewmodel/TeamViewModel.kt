package com.example.responsi1mobileh1d023120.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi1mobileh1d023120.data.model.Player
import com.example.responsi1mobileh1d023120.data.model.TeamResponse
import com.example.responsi1mobileh1d023120.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class TeamViewModel : ViewModel() {
    val teamData = MutableLiveData<TeamResponse?>()
    val squad = MutableLiveData<List<Player>>(emptyList())
    val error = MutableLiveData<String?>()

    fun loadTeam(teamId: Int = 113) {
        viewModelScope.launch {
            try {
                val resp = RetrofitInstance.api.getTeamDetail(teamId)
                if (resp.isSuccessful) {
                    val body = resp.body()
                    teamData.postValue(body)
                    squad.postValue(body?.squad ?: emptyList())
                    error.postValue(null)
                } else {
                    teamData.postValue(null)
                    squad.postValue(emptyList())
                    error.postValue("Failed to load team data: ${resp.code()}")
                }
            } catch (e: Exception) {
                teamData.postValue(null)
                squad.postValue(emptyList())
                error.postValue("Network error: ${e.message}")
            }
        }
    }
}
