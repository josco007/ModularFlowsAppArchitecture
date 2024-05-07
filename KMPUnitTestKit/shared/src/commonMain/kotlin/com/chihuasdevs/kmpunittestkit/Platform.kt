package com.chihuasdevs.kmpunittestkit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform