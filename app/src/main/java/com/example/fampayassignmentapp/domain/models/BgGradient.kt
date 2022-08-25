package com.example.fampayassignmentapp.domain.models

import com.google.gson.annotations.SerializedName

data class BgGradient(
    @field:SerializedName("colors")
    val colors : List<String>? = null,

    @field:SerializedName("angle")
    val angle : Int? = null
)