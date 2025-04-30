package com.zoroxnekko.palebluecmpassignment.pixa_images_overview.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PixaImageHitsDto(
    val hits: List<PixaImageDto>
)
