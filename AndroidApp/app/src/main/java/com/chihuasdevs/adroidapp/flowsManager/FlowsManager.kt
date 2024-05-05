package com.chihuasdevs.adroidapp.flowsManager

import android.content.Context
import android.util.Log
import com.chihuasdevs.adroidapp.flowsManager.flows.appFlow.AppFlowManager
import com.chihuasdevs.adroidapp.flowsManager.flows.homeFlow.HomeFlowManager
import com.chihuasdevs.adroidapp.flowsManager.flows.loginFlow.LoginFlowManager
import com.chihuasdevs.adroidapp.flowsManager.flows.registerFlow.RegisterFlowManager
import com.chihuasdevs.adroidapp.flowsManager.flows.spaceXFlow.SpaceXFlowManager
import com.chihuasdevs.adroidapp.flowsManager.flows.splashFlow.SplashFlowManager
import com.chihuasdevs.adroidapp.flowsManager.flows.tabBarFlow.TabBarFlowManager
import com.chihuasdevs.androidui.AndroidUI
import com.chihuasdevs.kmmCoreKit.base.BaseFlow
import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmbusinesskit.KMMBusinessKit
import com.chihuasdevs.kmmrepositorykit.KMMRepositoryKit
import com.chihuasdevs.kmmrepositorykit.shared.cache.DatabaseDriverFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FlowsManager(context: Context) : FlowManagerBase() {

    var businessKit: KMMBusinessKit? = null
    var repositorySDK: KMMRepositoryKit? = null

    init {
        //repositorySDK = RepositorySDK()
        AndroidUI.initUIKit(themeConf = AndroidUI.ThemeConf(AndroidUI.ThemeConf.ThemeType.THEME1))

//        businessKit = KMMBusinessKit(
//            businessAppFlowDelegate = UISDK.getBusinessAppFlowDelegate(),
//            businessFlowManagerDelegate = this,
//            businessToRepoDelegate = repositorySDK?.networkManager,
//            repoDataManager = repositorySDK?.repoDataManager)

        businessKit = KMMBusinessKit(this)
        repositorySDK = KMMRepositoryKit(DatabaseDriverFactory(context))

    }

    override fun getNewFlowObjFor(flow: Flow, animation: UIAnimation?): BaseFlow {
        return when (flow) {
            Flow.APP -> AppFlowManager(flowsManager = this)
            Flow.SPLASH -> SplashFlowManager(flowsManager = this)
            Flow.LOGIN -> LoginFlowManager(flowsManager = this, animation = animation)
            Flow.HOME -> HomeFlowManager(flowsManager = this, animation = animation)
//            Flow.MY_ACCOUNT -> MyAccountFlowManager(flowsManager = this)
//            Flow.DETAIL -> DetailFlowManager(flowsManager = this, animation = animation)
            Flow.REGISTER -> RegisterFlowManager(flowsManager = this, animation)
            Flow.TAB_BAR -> TabBarFlowManager(flowsManager = this, animation)
            Flow.SPACE_X -> SpaceXFlowManager(flowsManager = this, animation)

            else -> {
                BaseFlow()
            }
        }
    }

}