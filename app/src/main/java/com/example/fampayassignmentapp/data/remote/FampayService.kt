package com.example.fampayassignmentapp.data.remote


import com.example.fampayassignmentapp.data.remote.response.CardGroupResponse
import retrofit2.Response
import retrofit2.http.GET

interface FampayService {

    @GET("fefcfbeb-5c12-4722-94ad-b8f92caad1ad")
    suspend fun getCardGroups(): Response<CardGroupResponse>
}