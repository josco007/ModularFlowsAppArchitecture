package com.chihuasdevs.androidui.uiFlows.tabBarFlow

import com.chihuasdevs.androidui.AndroidUI
import com.chihuasdevs.androidui.views.FlowViews.tabBarView.TabBarView
import com.chihuasdevs.androidui.views.FlowViews.tabBarView.TabBarViewModel
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessTabBarDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.TabBarUIEventsDelegate


class UITabBarFlow {

    private var tabBarViewModel: TabBarViewModel? = null
    var tabBarView: TabBarView? = null

    fun start(tabBarUIEventsDelegate: TabBarUIEventsDelegate?, animation: UIAnimation?) {
        tabBarViewModel = TabBarViewModel(tabBarUIEventsDelegate)
        tabBarViewModel?.let { viewModel ->
            tabBarView = TabBarView(viewModel)

        }
        openTabBar(animation)
    }

    fun resume(animation: UIAnimation?){
        openTabBar(animation)
    }

    private fun openTabBar(animation: UIAnimation?) {
        AndroidUI.getAppViewModel()?.setCurrentView({ tabBarView?.ComposableView() }, animation)
    }

    fun getBusinessTabBarDelegate(): BusinessTabBarDelegate? {
        return tabBarViewModel
    }
}