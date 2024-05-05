package com.chihuasdevs.kmmbusinesskit.flows.loginFlow

import com.chihuasdevs.kmmCoreKit.base.BusinessBaseFlow
import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.businessToRepo.UserWebservicesDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessAppViewDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessLoginDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.LoginUIEventsDelegate
import com.chihuasdevs.kmmCoreKit.models.webServices.userWS.loginModels.request.LoginRequest
import com.chihuasdevs.kmmbusinesskit.KMMBusinessKit

class LoginBusinessFlow: BusinessBaseFlow(), LoginUIEventsDelegate {
    private var businessKit: KMMBusinessKit? = null
    private var businessAppViewDelegate: BusinessAppViewDelegate? = null
    private var businessLoginDelegate: BusinessLoginDelegate? = null
    private var userWebservicesDelegate: UserWebservicesDelegate? = null

    fun start(businessKit: KMMBusinessKit?,
              businessAppViewDelegate: BusinessAppViewDelegate?,
              businessLoginDelegate: BusinessLoginDelegate?,
              userWebservicesDelegate: UserWebservicesDelegate?){
        this.businessKit = businessKit
        this.businessLoginDelegate = businessLoginDelegate
        this.businessAppViewDelegate = businessAppViewDelegate
        this.userWebservicesDelegate = userWebservicesDelegate
    }

    //LoginUIEventsDelegate
    override fun onAppear() {

    }

    override suspend fun onLogin(user: String, password: String) {

        val request = LoginRequest(user, password)

        var idLoading = businessAppViewDelegate?.showLoading(this, true, null)
        this.userWebservicesDelegate?.login(request)
        businessAppViewDelegate?.showLoading(this, false, idLoading)

        businessKit?.flowManager?.actionForFlow(FlowManagerBase.Flow.TAB_BAR, FlowManagerBase.FlowAction.START, null)
        businessKit?.flowManager?.actionForFlow(FlowManagerBase.Flow.HOME, FlowManagerBase.FlowAction.START, UIAnimation.FORWARD)
        businessKit?.flowManager?.actionForFlow(FlowManagerBase.Flow.LOGIN, FlowManagerBase.FlowAction.DESTROY)
    }

    override fun onRegisterBtnTapped() {
        businessKit?.flowManager?.actionForFlow(FlowManagerBase.Flow.REGISTER, FlowManagerBase.FlowAction.START, UIAnimation.FORWARD)
    }
}

