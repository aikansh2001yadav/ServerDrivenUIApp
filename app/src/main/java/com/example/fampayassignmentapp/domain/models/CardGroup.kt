package com.example.fampayassignmentapp.domain.models

import com.google.gson.annotations.SerializedName

data class CardGroup(

    @field:SerializedName("cards")
    val cards: ArrayList<CardItem?>? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("design_type")
    val designType: String? = null,

    @field:SerializedName("is_scrollable")
    val isScrollable: Boolean? = null,

    @field:SerializedName("height")
    val height: Int? = null
)


