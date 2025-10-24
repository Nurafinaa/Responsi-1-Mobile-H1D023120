package com.example.responsi1mobileh1d023120.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import com.example.responsi1mobileh1d023120.utils.Constants
import com.example.responsi1mobileh1d023120.data.model.TeamResponse

interface ApiService {
    @Headers("X-Auth-Token: ${Constants.API_KEY}")
    @GET("teams/{id}")
    suspend fun getTeamDetail(@Path("id") id: Int): Response<TeamResponse>
}