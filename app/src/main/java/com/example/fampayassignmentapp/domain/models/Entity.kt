package com.example.fampayassignmentapp.domain.models

import com.google.gson.annotations.SerializedName

data class Entity(

    @field:SerializedName("color")
    val color: String? = null,

    @field:SerializedName("text")
    val text: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("font_style")
    val fontStyle: String? = null
)
