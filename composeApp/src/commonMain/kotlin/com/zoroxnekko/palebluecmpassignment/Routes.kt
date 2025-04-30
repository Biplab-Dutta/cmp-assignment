package com.zoroxnekko.palebluecmpassignment

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object PixaImageOverviewPage : Route
}