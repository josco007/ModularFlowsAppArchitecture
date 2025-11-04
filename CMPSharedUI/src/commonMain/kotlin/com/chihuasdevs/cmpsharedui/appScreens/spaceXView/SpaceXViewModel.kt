package com.chihuasdevs.cmpsharedui.appScreens.spaceXView

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessLoginDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessSpaceXDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.LoginUIEventsDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.SpaceXUIEventsDelegate
import com.chihuasdevs.kmmCoreKit.models.webServices.spacexWS.RocketLaunch
import kotlinx.coroutines.launch


class SpaceXViewModel(): BusinessSpaceXDelegate {

    var spaceXUIEventsDelegate: SpaceXUIEventsDelegate? = null

    private val _rocketLaunches = mutableStateOf<List<RocketLaunch>>(emptyList())
    val rocketLaunches: MutableState<List<RocketLaunch>> = _rocketLaunches

    constructor(spaceXUIEventsDelegate: SpaceXUIEventsDelegate? = null) : this() {
       this.spaceXUIEventsDelegate = spaceXUIEventsDelegate
    }


    override fun showRocketLaunches(rocketLaunches: List<RocketLaunch>) {
        _rocketLaunches.value = rocketLaunches
    }
}