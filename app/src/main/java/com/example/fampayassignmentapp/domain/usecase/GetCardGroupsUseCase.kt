package com.example.fampayassignmentapp.domain.usecase

import com.example.fampayassignmentapp.commons.Resource
import com.example.fampayassignmentapp.commons.returnAuthOrServerErrorListResponse
import com.example.fampayassignmentapp.commons.returnExceptionResponse
import com.example.fampayassignmentapp.domain.models.CardGroup
import com.example.fampayassignmentapp.domain.repository.FampayRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCardGroupsUseCase @Inject constructor(private val repository: FampayRepo) {
    operator fun invoke(): Flow<Resource<List<CardGroup>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getCardGroups()
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.response!!))
            } else {
                emit(returnAuthOrServerErrorListResponse<List<CardGroup>>(response))
            }
        } catch (e: Exception) {
            emit(returnExceptionResponse<List<CardGroup>>(e))
        }
    }
}