package com.chihuasdevs.kmmbusinesskit.flows.spaceXFlow

import com.chihuasdevs.kmmCoreKit.base.BusinessBaseFlow
import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.businessToRepo.SpaceXWebservicesDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToRepo.UserWebservicesDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessAppViewDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessSpaceXDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.SpaceXUIEventsDelegate

import com.chihuasdevs.kmmbusinesskit.KMMBusinessKit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SpaceXBusinessFlow: BusinessBaseFlow(), SpaceXUIEventsDelegate {

    private var businessKit: KMMBusinessKit? = null
    private var businessAppViewDelegate: BusinessAppViewDelegate? = null
    private var businessSpaceXDelegate: BusinessSpaceXDelegate? = null
    private var spacexWebservicesDelegate: SpaceXWebservicesDelegate? = null

    fun start(businessKit: KMMBusinessKit?,
              businessAppViewDelegate: BusinessAppViewDelegate?,
              businessSpaceXDelegate: BusinessSpaceXDelegate?,
              spacexWebservicesDelegate: SpaceXWebservicesDelegate?){
        this.businessKit = businessKit
        this.businessSpaceXDelegate = businessSpaceXDelegate
        this.businessAppViewDelegate = businessAppViewDelegate
        this.spacexWebservicesDelegate = spacexWebservicesDelegate

        showRocketLaunches()
    }

    fun showRocketLaunches() {
        val loadingId = businessAppViewDelegate?.showLoading(this, true, null)
        MainScope().launch {
            val launches = spacexWebservicesDelegate?.getLaunches(false)
            withContext(Dispatchers.Main) {
                launches?.let { businessSpaceXDelegate?.showRocketLaunches(it) }
                businessAppViewDelegate?.showLoading(this@SpaceXBusinessFlow, false, loadingId)
            }
        }
    }

    override fun onBackTapped() {
        businessKit?.flowManager?.actionForFlow(FlowManagerBase.Flow.HOME, FlowManagerBase.FlowAction.RESUME, UIAnimation.BACK)
    }

}

