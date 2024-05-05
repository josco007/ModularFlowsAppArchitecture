package com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness


import com.chihuasdevs.kmmCoreKit.base.CoreKitBaseViewModel

interface PasswordUIEventsDelegate {
    fun onNextTapped(userDataViewModel: CoreKitBaseViewModel?, password: String)
}