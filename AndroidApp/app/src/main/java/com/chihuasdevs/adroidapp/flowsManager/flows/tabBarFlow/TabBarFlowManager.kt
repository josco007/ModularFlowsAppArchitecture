package com.chihuasdevs.adroidapp.flowsManager.flows.tabBarFlow

import com.chihuasdevs.adroidapp.flowsManager.FlowsManager
import com.chihuasdevs.adroidapp.flowsManager.flows.appFlow.AppFlowManager
import com.chihuasdevs.androidui.uiFlows.tabBarFlow.UITabBarFlow
import com.chihuasdevs.kmmCoreKit.base.BaseFlow
import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmbusinesskit.flows.tabBarFlow.TabBarBusinessFlow

class TabBarFlowManager(flowsManager: FlowsManager?, animation: UIAnimation?) : BaseFlow() {


    var uiTabBarFlow = UITabBarFlow()

    init {
        // Crear UI para el flujo de tabBar
        uiTabBarFlow = UITabBarFlow()

        // Crear negocio para el flujo de tabBar
        val businessTabBarFlow = TabBarBusinessFlow()

        // Iniciar el flujo de UI de tabBar con el flujo de negocio de tabBar
        uiTabBarFlow.start(tabBarUIEventsDelegate = businessTabBarFlow, animation)

        businessTabBarFlow.start(flowsManager?.businessKit,
            businessTabBarDelegate = uiTabBarFlow.getBusinessTabBarDelegate())

    }

    override fun resume(animation: UIAnimation?) {
        super.resume(animation)
        uiTabBarFlow.resume(animation)
    }
}