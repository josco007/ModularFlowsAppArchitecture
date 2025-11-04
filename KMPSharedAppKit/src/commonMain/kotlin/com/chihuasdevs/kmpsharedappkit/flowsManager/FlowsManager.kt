package com.chihuasdevs.kmmflowskit

import com.chihuasdevs.adroidapp.flowsManager.flows.appFlow.AppFlowManager
import com.chihuasdevs.adroidapp.flowsManager.flows.homeFlow.HomeFlowManager
import com.chihuasdevs.adroidapp.flowsManager.flows.loginFlow.LoginFlowManager
import com.chihuasdevs.adroidapp.flowsManager.flows.registerFlow.RegisterFlowManager
import com.chihuasdevs.adroidapp.flowsManager.flows.spaceXFlow.SpaceXFlowManager
import com.chihuasdevs.adroidapp.flowsManager.flows.splashFlow.SplashFlowManager
import com.chihuasdevs.adroidapp.flowsManager.flows.tabBarFlow.TabBarFlowManager
import com.chihuasdevs.cmpsharedui.CMPSharedUI
import com.chihuasdevs.kmmCoreKit.base.BaseFlow
import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmbusinesskit.KMMBusinessKit
import com.chihuasdevs.kmmrepositorykit.KMMRepositoryKit
import com.chihuasdevs.kmmrepositorykit.shared.cache.DatabaseDriverFactory


class FlowsManager(private val driverFactory: DatabaseDriverFactory) : FlowManagerBase() {

    var businessKit: KMMBusinessKit? = null
    var repositoryKit: KMMRepositoryKit? = null

    init {
        // Inicializar UI Theme
        CMPSharedUI.initUIKit(
            themeConf = CMPSharedUI.ThemeConf(CMPSharedUI.ThemeConf.ThemeType.THEME1)
        )

        // Inicializar repositorio y negocio
        repositoryKit = KMMRepositoryKit(driverFactory)
        businessKit = KMMBusinessKit(this)
    }

    override fun getNewFlowObjFor(flow: Flow, animation: UIAnimation?): BaseFlow {
        return when (flow) {
            Flow.APP -> AppFlowManager(flowsManager = this)
            Flow.SPLASH -> SplashFlowManager(flowsManager = this)
            Flow.LOGIN -> LoginFlowManager(flowsManager = this, animation = animation)
            Flow.HOME -> HomeFlowManager(flowsManager = this, animation = animation)
            Flow.REGISTER -> RegisterFlowManager(flowsManager = this, animation)
            Flow.TAB_BAR -> TabBarFlowManager(flowsManager = this, animation)
            Flow.SPACE_X -> SpaceXFlowManager(flowsManager = this, animation)
            else -> BaseFlow()
        }
    }
}
