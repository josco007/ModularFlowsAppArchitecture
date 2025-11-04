package com.chihuasdevs.adroidapp.flowsManager.flows.homeFlow

import com.chihuasdevs.adroidapp.flowsManager.flows.appFlow.AppFlowManager
import com.chihuasdevs.adroidapp.flowsManager.flows.tabBarFlow.TabBarFlowManager
import com.chihuasdevs.cmpsharedui.uiFlows.homeFlow.UIHomeFlow
import com.chihuasdevs.kmmCoreKit.base.BaseFlow
import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmbusinesskit.flows.homeFlow.HomeBusinessFlow
import com.chihuasdevs.kmmbusinesskit.flows.tabBarFlow.TabBarBusinessFlow
import com.chihuasdevs.kmmflowskit.FlowsManager

class HomeFlowManager(flowsManager: FlowsManager?, animation: UIAnimation?) : BaseFlow() {

    private var uiHomeFlow: UIHomeFlow? = null

    init {
        // Crear UI para el flujo de home
        uiHomeFlow = UIHomeFlow()

        // Crear negocio para el flujo de home
        val businessHomeFlow = HomeBusinessFlow()

        // Iniciar el flujo de UI de home con el flujo de negocio de home

        uiHomeFlow?.start(homeUIEventsDelegate = businessHomeFlow,
            (flowsManager?.getFlow(FlowManagerBase.Flow.TAB_BAR, null , null) as TabBarFlowManager).uiTabBarFlow?.tabBarView, animation)

        // Iniciar el flujo de negocio de home con la pantalla de home de UI
//        businessHomeFlow.start(accountApiRepositoryDelegate = flowsManager?.repositorySDK?.networkManager,
//            firebaseRepositoryDelegate = null,
//            businessHomeDelegate = uiHomeFlow.getBusinessHomeDelegate())

        businessHomeFlow.start(flowsManager?.businessKit, businessHomeDelegate = uiHomeFlow?.getBusinessHomeDelegate())
    }

    override fun resume(animation: UIAnimation?) {
        super.resume(animation)
        uiHomeFlow?.resume(animation)
    }
}