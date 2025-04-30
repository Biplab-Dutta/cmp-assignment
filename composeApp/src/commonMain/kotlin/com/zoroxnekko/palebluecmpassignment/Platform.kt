package com.zoroxnekko.palebluecmpassignment

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform