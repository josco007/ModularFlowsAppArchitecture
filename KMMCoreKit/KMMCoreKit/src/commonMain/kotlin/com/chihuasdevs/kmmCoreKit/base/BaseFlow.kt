package com.chihuasdevs.kmmCoreKit.base

import com.chihuasdevs.kmmCoreKit.enum.UIAnimation

open class BaseFlow {

    open fun getBusinessBaseFlow(): BusinessBaseFlow? {
        return null
    }

    open fun resume(animation: UIAnimation?) {
        // No hace nada por defecto
    }
}