package com.chihuasdevs.cmpsharedui.appScreens.splashView

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessSplashDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.SplashUIEventsDelegate


class SplashViewModel() : BusinessSplashDelegate {

    var test: MutableState<Int> = mutableStateOf(0)

    private var splashUIEventsDelegate: SplashUIEventsDelegate? = null

    constructor(splashUIEventsDelegate: SplashUIEventsDelegate? = null) : this() {
        this.splashUIEventsDelegate = splashUIEventsDelegate
    }

    fun setSplashUIEventsDelegate(splashUIEventsDelegate: SplashUIEventsDelegate?) {
        this.splashUIEventsDelegate = splashUIEventsDelegate
    }

    fun onSplashAppear() {
        splashUIEventsDelegate?.onAppear()
    }

}