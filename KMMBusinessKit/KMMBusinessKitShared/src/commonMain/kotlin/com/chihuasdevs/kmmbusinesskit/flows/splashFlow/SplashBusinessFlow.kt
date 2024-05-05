package com.chihuasdevs.kmmbusinesskit.flows.splashFlow

import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessSplashDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.SplashUIEventsDelegate
import com.chihuasdevs.kmmbusinesskit.KMMBusinessKit

class SplashBusinessFlow: SplashUIEventsDelegate {
    private var businessKit: KMMBusinessKit? = null
    private var businessSplashDelegate: BusinessSplashDelegate? = null

    fun start(businessKit: KMMBusinessKit?,
              businessSplashDelegate: BusinessSplashDelegate?){
        this.businessKit = businessKit
        this.businessSplashDelegate = businessSplashDelegate

        checkSession()
    }

    private fun checkSession(){

        this.businessKit?.
        flowManager?.
        actionForFlow(FlowManagerBase.Flow.LOGIN, FlowManagerBase.FlowAction.START, UIAnimation.FORWARD, false, null )
    }

    //SplashUIEventsDelegate
    override fun onAppear() {

    }
}

