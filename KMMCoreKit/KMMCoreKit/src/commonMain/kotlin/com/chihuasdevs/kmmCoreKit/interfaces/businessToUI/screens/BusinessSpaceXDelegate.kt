package com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens

import com.chihuasdevs.kmmCoreKit.models.webServices.spacexWS.RocketLaunch

interface BusinessSpaceXDelegate {
    fun showRocketLaunches(rocketLaunches: List<RocketLaunch>)
}