package com.chihuasdevs.kmmrepositorykit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform