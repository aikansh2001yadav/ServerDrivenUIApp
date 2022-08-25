package com.example.fampayassignmentapp.data.remote.response

import com.example.fampayassignmentapp.domain.models.CardGroup
import com.google.gson.annotations.SerializedName

data class CardGroupResponse(
    @field:SerializedName("card_groups")
    val response: List<CardGroup>? = null
)