package com.example.fampayassignmentapp.domain.models

import com.google.gson.annotations.SerializedName


data class CardItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("icon")
    val icon: Icon? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field : SerializedName("formatted_description")
    val formattedDescription: FormattedContent? = null,

    @field:SerializedName("formatted_title")
    val formattedContent: FormattedContent? = null,

    @field:SerializedName("bg_image")
    val bgImage: BgImage? = null,

    @field:SerializedName("bg_color")
    val bgColor: String? = null,

    @field:SerializedName("cta")
    val cta: List<CTAItem?>? = null,

    @field:SerializedName("bg_gradient")
    val bgGradient: BgGradient? = null,

    @field:SerializedName("is_disabled")
    val isDisabled: Boolean? = null
)