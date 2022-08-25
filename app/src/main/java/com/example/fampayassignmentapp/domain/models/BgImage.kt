package com.example.fampayassignmentapp.domain.models

import com.google.gson.annotations.SerializedName

data class BgImage(

    @field:SerializedName("aspect_ratio")
    val aspectRatio: Double? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("image_type")
    val imageType: String? = null,

    @field:SerializedName("asset_type")
    val assetType: String? = null
)
