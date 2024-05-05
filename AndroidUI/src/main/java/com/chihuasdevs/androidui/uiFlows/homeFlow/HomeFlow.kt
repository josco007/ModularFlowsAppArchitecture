package com.chihuasdevs.androidui.uiFlows.homeFlow

import com.chihuasdevs.androidui.AndroidUI
import com.chihuasdevs.androidui.appScreens.homeView.HomeView
import com.chihuasdevs.androidui.appScreens.homeView.HomeViewModel
import com.chihuasdevs.androidui.views.FlowViews.tabBarView.TabBarView
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessHomeDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.HomeUIEventsDelegate

class UIHomeFlow {

    private var homeViewModel: HomeViewModel? = null
    private var tabBarView: TabBarView? = null

    fun start(homeUIEventsDelegate: HomeUIEventsDelegate?,
              tabBarView: TabBarView?,
               animation: UIAnimation?) {

        homeViewModel = HomeViewModel(homeUIEventsDelegate)
        this.tabBarView = tabBarView
        openHome(animation)
    }

    private fun openHome(animation: UIAnimation?) {
        homeViewModel?.let { viewModel ->
            val homeView = HomeView(viewModel, tabBarView)

            AndroidUI.getAppViewModel()?.setCurrentView({ homeView.ComposableView()}, animation)
        }

    }

    fun resume(animation: UIAnimation?){
        openHome(animation)
    }

    fun getBusinessHomeDelegate(): BusinessHomeDelegate? {
        return homeViewModel
    }
}