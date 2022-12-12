package com.harmittaa.multipaltformcolors

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
