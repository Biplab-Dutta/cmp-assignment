package com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation

sealed interface PixaImagesAction {
    data class OnFetchImages(val query: String, val imageType: String) : PixaImagesAction
}
