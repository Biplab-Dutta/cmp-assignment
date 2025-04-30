package com.zoroxnekko.palebluecmpassignment.pixa_images_overview.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PixaImageDto(
    val id: Int,
    @SerialName("user") val userName: String,
    @SerialName("previewURL") val imageUrl: String,
)
