package com.example.fampayassignmentapp.domain.models

import com.google.gson.annotations.SerializedName

data class FormattedContent(

    @field:SerializedName("entities")
    val entities: List<Entity>? = null,

    @field:SerializedName("text")
    val text: String? = null,

    @field:SerializedName("align")
    val align: String? = null
)