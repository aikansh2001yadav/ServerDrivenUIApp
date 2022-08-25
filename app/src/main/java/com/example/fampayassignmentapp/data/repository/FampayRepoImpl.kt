package com.example.fampayassignmentapp.data.repository

import com.example.fampayassignmentapp.data.remote.FampayService
import com.example.fampayassignmentapp.data.remote.response.CardGroupResponse
import com.example.fampayassignmentapp.domain.repository.FampayRepo
import retrofit2.Response

class FampayRepoImpl(private val service: FampayService) : FampayRepo {
    override suspend fun getCardGroups(): Response<CardGroupResponse> {
        return service.getCardGroups()
    }
}