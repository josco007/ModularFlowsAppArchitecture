package com.chihuasdevs.kmmbusinesskit.flows.homeFlow

import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessHomeDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.HomeUIEventsDelegate
import com.chihuasdevs.kmmbusinesskit.KMMBusinessKit

class HomeBusinessFlow: HomeUIEventsDelegate {
    private var businessKit: KMMBusinessKit? = null
    private var businessHomeDelegate: BusinessHomeDelegate? = null

    fun start(businessKit: KMMBusinessKit?,
              businessHomeDelegate: BusinessHomeDelegate?){
        this.businessKit = businessKit
        this.businessHomeDelegate = businessHomeDelegate

    }

    override fun onBackTapped() {
        businessKit?.flowManager?.actionForFlow(FlowManagerBase.Flow.LOGIN, FlowManagerBase.FlowAction.RESUME, UIAnimation.BACK)
    }

    override fun onSpaceXRocketLaunchesTapped() {
        businessKit?.flowManager?.actionForFlow(FlowManagerBase.Flow.SPACE_X, FlowManagerBase.FlowAction.START, UIAnimation.FORWARD)
    }
}
