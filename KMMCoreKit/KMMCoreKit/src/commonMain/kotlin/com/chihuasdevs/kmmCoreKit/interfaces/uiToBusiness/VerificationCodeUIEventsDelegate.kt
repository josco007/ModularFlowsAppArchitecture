package com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness

import com.chihuasdevs.kmmCoreKit.base.CoreKitBaseViewModel

interface VerificationCodeUIEventsDelegate {
    fun onNextTapped(userDataViewModel: CoreKitBaseViewModel?, code: String)
}