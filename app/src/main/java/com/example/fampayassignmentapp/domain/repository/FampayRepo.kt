package com.example.fampayassignmentapp.domain.repository

import com.example.fampayassignmentapp.data.remote.response.CardGroupResponse
import retrofit2.Response

interface FampayRepo {

    suspend fun getCardGroups(): Response<CardGroupResponse>
}