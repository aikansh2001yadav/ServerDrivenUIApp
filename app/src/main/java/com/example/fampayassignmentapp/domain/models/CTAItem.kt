package com.example.fampayassignmentapp.domain.models

import com.google.gson.annotations.SerializedName

data class CTAItem(

    @field:SerializedName("bg_color")
    val bgColor: String? = null,

    @field:SerializedName("text")
    val text: String? = null,

    @field:SerializedName("text_color")
    val textColor: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)