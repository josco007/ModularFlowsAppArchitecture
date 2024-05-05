package com.chihuasdevs.androidui.appScreens.homeView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessHomeDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.HomeUIEventsDelegate

class HomeViewModel(): ViewModel(), BusinessHomeDelegate {


    var homeUIEventsDelegate: HomeUIEventsDelegate? = null

    var username by mutableStateOf("")
    var password by mutableStateOf("")



    constructor(homeUIEventsDelegate: HomeUIEventsDelegate? = null) : this() {
        this.homeUIEventsDelegate = homeUIEventsDelegate
    }

}