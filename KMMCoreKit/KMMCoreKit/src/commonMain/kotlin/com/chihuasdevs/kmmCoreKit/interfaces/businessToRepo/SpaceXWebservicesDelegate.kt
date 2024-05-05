package com.chihuasdevs.kmmCoreKit.interfaces.businessToRepo

import com.chihuasdevs.kmmCoreKit.models.webServices.spacexWS.RocketLaunch

interface SpaceXWebservicesDelegate {
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch>
}