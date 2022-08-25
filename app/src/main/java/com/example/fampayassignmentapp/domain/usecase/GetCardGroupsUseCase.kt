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
                val designTypesInSequence = arrayListOf<String>("HC3", "HC6", "HC5", "HC9", "HC1")
                val cardGroupList = response.body()!!.response

                val newCardGroupList = arrayListOf<CardGroup>()

                for (designType in designTypesInSequence) {
                    for (cardGroup in cardGroupList!!) {
                        if (cardGroup.designType.equals(designType, ignoreCase = true)) {
                            newCardGroupList.add(cardGroup)
                        }
                    }
                }
                emit(Resource.Success(newCardGroupList))
            } else {
                emit(returnAuthOrServerErrorListResponse<List<CardGroup>>(response))
            }
        } catch (e: Exception) {
            emit(returnExceptionResponse<List<CardGroup>>(e))
        }
    }
}