package com.chihuasdevs.adroidapp.flowsManager.flows.spaceXFlow

import com.chihuasdevs.adroidapp.flowsManager.flows.appFlow.AppFlowManager
import com.chihuasdevs.cmpsharedui.uiFlows.spaceXFlow.UISpaceXFlow
import com.chihuasdevs.kmmCoreKit.base.BaseFlow
import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmbusinesskit.flows.spaceXFlow.SpaceXBusinessFlow
import com.chihuasdevs.kmmflowskit.FlowsManager


class SpaceXFlowManager(flowsManager: FlowsManager?, animation: UIAnimation?) : BaseFlow() {


    var uiSpaceXFlow = UISpaceXFlow()
    init {
        // Crear UI para el flujo de spaceX
        uiSpaceXFlow = UISpaceXFlow()

        // Crear negocio para el flujo de spaceX
        val businessSpaceXFlow = SpaceXBusinessFlow()

        // Iniciar el flujo de UI de spaceX con el flujo de negocio de spaceX
        uiSpaceXFlow.start(spaceXUIEventsDelegate = businessSpaceXFlow, animation)

        // Iniciar el flujo de negocio de spaceX con la pantalla de spaceX de UI
//        businessSpaceXFlow.start(accountApiRepositoryDelegate = flowsManager?.repositorySDK?.networkManager,
//            firebaseRepositoryDelegate = null,
//            businessSpaceXDelegate = uiSpaceXFlow.getBusinessSpaceXDelegate())

        businessSpaceXFlow.start(flowsManager?.businessKit,
            (flowsManager?.getFlow(FlowManagerBase.Flow.APP, null , null) as AppFlowManager).getUIAppFlow()?.getBusinessAppViewDelegate(),
            businessSpaceXDelegate = uiSpaceXFlow.getBusinessSpaceXDelegate(),
            spacexWebservicesDelegate = flowsManager?.repositorySDK?.getSpaceXWS())

    }

    override fun resume(animation: UIAnimation?) {
        super.resume(animation)
        uiSpaceXFlow.resume(animation)
    }
}