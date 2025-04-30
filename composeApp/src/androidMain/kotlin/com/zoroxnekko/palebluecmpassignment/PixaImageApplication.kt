package com.zoroxnekko.palebluecmpassignment

import android.app.Application
import com.zoroxnekko.palebluecmpassignment.di.initKoin
import org.koin.android.ext.koin.androidContext

class PixaImageApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@PixaImageApplication)
        }
    }
}