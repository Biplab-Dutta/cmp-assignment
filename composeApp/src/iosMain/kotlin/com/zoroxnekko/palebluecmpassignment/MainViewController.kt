package com.zoroxnekko.palebluecmpassignment

import androidx.compose.ui.window.ComposeUIViewController
import com.zoroxnekko.palebluecmpassignment.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }