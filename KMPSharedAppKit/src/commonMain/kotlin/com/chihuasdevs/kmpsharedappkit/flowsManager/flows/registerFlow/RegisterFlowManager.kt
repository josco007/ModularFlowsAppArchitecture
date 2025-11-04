package com.chihuasdevs.adroidapp.flowsManager.flows.registerFlow

import com.chihuasdevs.adroidapp.flowsManager.flows.appFlow.AppFlowManager
import com.chihuasdevs.cmpsharedui.uiFlows.registerFlow.UIRegisterFlow
import com.chihuasdevs.kmmCoreKit.base.BaseFlow
import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmbusinesskit.flows.registerFlow.RegisterBusinessFlow
import com.chihuasdevs.kmmbusinesskit.flows.registerFlow.uiEvents.RegisterBusinessFlowPasswordUIEventsDelExt.passwordUIEventsDelegate
import com.chihuasdevs.kmmbusinesskit.flows.registerFlow.uiEvents.RegisterBusinessFlowUserInfoUIEventsDelExt.userInfoUIEventsDelegate
import com.chihuasdevs.kmmbusinesskit.flows.registerFlow.uiEvents.RegisterBusinessFlowUserInfoUIEventsDelExt.verificationCodeUIEventsDelegate
import com.chihuasdevs.kmmflowskit.FlowsManager

class RegisterFlowManager(flowsManager: FlowsManager?, animation: UIAnimation?) : BaseFlow() {


    var uiRegisterFlow = UIRegisterFlow()
    init {
        // Crear UI para el flujo de register
        uiRegisterFlow = UIRegisterFlow()

        // Crear negocio para el flujo de register
        val businessRegisterFlow = RegisterBusinessFlow()

        // Iniciar el flujo de UI de register con el flujo de negocio de register
        uiRegisterFlow.start(businessRegisterFlow.userInfoUIEventsDelegate,
            businessRegisterFlow.passwordUIEventsDelegate,
            businessRegisterFlow.verificationCodeUIEventsDelegate,
            animation)

        // Iniciar el flujo de negocio de register con la pantalla de register de UI
//        businessRegisterFlow.start(accountApiRepositoryDelegate = flowsManager?.repositorySDK?.networkManager,
//            firebaseRepositoryDelegate = null,
//            businessRegisterDelegate = uiRegisterFlow.getBusinessRegisterDelegate())

        businessRegisterFlow.start(flowsManager?.businessKit,
            (flowsManager?.getFlow(FlowManagerBase.Flow.APP, null , null) as AppFlowManager).getUIAppFlow()?.getBusinessAppViewDelegate(),
            uiRegisterFlow,
            uiRegisterFlow?.getBusinessUserInfoDelegate(),
            uiRegisterFlow?.getBusinessPasswordDelegate(),
            uiRegisterFlow?.getBusinessVerificationCodeDelegate(),
            flowsManager?.repositoryKit?.getOtpWS())

    }

    override fun resume(animation: UIAnimation?) {
        super.resume(animation)
        uiRegisterFlow.resume(animation)
    }
}