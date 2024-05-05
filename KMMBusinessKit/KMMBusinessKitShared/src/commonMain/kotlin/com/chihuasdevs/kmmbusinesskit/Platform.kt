package com.chihuasdevs.kmmbusinesskit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform