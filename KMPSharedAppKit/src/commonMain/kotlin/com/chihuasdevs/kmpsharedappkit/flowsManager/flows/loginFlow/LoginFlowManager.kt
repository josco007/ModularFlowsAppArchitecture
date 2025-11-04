package com.chihuasdevs.adroidapp.flowsManager.flows.loginFlow

import com.chihuasdevs.adroidapp.flowsManager.flows.appFlow.AppFlowManager
import com.chihuasdevs.cmpsharedui.uiFlows.loginFlow.UILoginFlow
import com.chihuasdevs.kmmCoreKit.base.BaseFlow
import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmbusinesskit.flows.loginFlow.LoginBusinessFlow
import com.chihuasdevs.kmmflowskit.FlowsManager

class LoginFlowManager(flowsManager: FlowsManager?, animation: UIAnimation?) : BaseFlow() {


    var uiLoginFlow = UILoginFlow()
    init {
        // Crear UI para el flujo de login
        uiLoginFlow = UILoginFlow()

        // Crear negocio para el flujo de login
        val businessLoginFlow = LoginBusinessFlow()

        // Iniciar el flujo de UI de login con el flujo de negocio de login
        uiLoginFlow.start(loginUIEventsDelegate = businessLoginFlow, animation)

        // Iniciar el flujo de negocio de login con la pantalla de login de UI
//        businessLoginFlow.start(accountApiRepositoryDelegate = flowsManager?.repositorySDK?.networkManager,
//            firebaseRepositoryDelegate = null,
//            businessLoginDelegate = uiLoginFlow.getBusinessLoginDelegate())

        businessLoginFlow.start(flowsManager?.businessKit,
            businessLoginDelegate = uiLoginFlow.getBusinessLoginDelegate(),
            businessAppViewDelegate = (flowsManager?.getFlow(FlowManagerBase.Flow.APP, null , null) as AppFlowManager).getUIAppFlow()?.getBusinessAppViewDelegate(),
            userWebservicesDelegate = flowsManager.repositoryKit?.getUserWS())

    }

    override fun resume(animation: UIAnimation?) {
        super.resume(animation)
        uiLoginFlow.resume(animation)
    }
}