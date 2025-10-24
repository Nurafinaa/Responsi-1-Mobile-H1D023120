package com.example.responsi1mobileh1d023120.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi1mobileh1d023120.data.model.TeamResponse
import com.example.responsi1mobileh1d023120.data.model.Coach
import com.example.responsi1mobileh1d023120.data.network.RetrofitInstance
import com.example.responsi1mobileh1d023120.viewmodel.CoachViewModel
import kotlinx.coroutines.launch

class CoachViewModel : ViewModel() {
    val teamData = MutableLiveData<TeamResponse?>()
    val coach = MutableLiveData<Coach?>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String?>()

    fun getTeam(id: Int) {
        viewModelScope.launch {
            try {
                loading.postValue(true)
                val response = RetrofitInstance.api.getTeamDetail(id)
                if (response.isSuccessful) {
                    val team = response.body()
                    teamData.postValue(team)
                    coach.postValue(team?.coach)
                    error.postValue(null)
                } else {
                    teamData.postValue(null)
                    coach.postValue(null)
                    error.postValue("Failed to load team data")
                }
            } catch (e: Exception) {
                teamData.postValue(null)
                coach.postValue(null)
                error.postValue("Network error: ${e.message}")
            } finally {
                loading.postValue(false)
            }
        }
    }

    fun fetchTeam() = getTeam(113)
}
