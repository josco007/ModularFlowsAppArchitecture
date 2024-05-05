package com.chihuasdevs.kmmCoreKit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform