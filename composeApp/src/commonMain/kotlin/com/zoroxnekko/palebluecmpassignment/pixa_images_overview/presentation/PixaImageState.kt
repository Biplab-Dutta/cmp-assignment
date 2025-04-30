package com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation

import com.zoroxnekko.palebluecmpassignment.core.presentation.UiText
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.domain.PixaImage

data class PixaImagesState(
    val results: List<PixaImage> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
)
