package com.chihuasdevs.adroidapp.flowsManager.flows.splashFlow

import com.chihuasdevs.cmpsharedui.uiFlows.splashFlow.UISplashFlow
import com.chihuasdevs.kmmCoreKit.base.BaseFlow
import com.chihuasdevs.kmmbusinesskit.flows.splashFlow.SplashBusinessFlow
import com.chihuasdevs.kmmflowskit.FlowsManager

class SplashFlowManager(flowsManager: FlowsManager?) : BaseFlow() {

    init {
        // Crear UI para el flujo de splash
        val uiSplashFlow = UISplashFlow()

        // Crear negocio para el flujo de splash
        val businessSplashFlow = SplashBusinessFlow()

        // Iniciar el flujo de UI de splash con el flujo de negocio de splash
        uiSplashFlow.start(splashUIEventsDelegate = businessSplashFlow)

        // Iniciar el flujo de negocio de splash con la pantalla de splash de UI
//        businessSplashFlow.start(accountApiRepositoryDelegate = flowsManager?.repositorySDK?.networkManager,
//            firebaseRepositoryDelegate = null,
//            businessSplashDelegate = uiSplashFlow.getBusinessSplashDelegate())

        businessSplashFlow.start(flowsManager?.businessKit, businessSplashDelegate = uiSplashFlow.getBusinessSplashDelegate())
    }
}