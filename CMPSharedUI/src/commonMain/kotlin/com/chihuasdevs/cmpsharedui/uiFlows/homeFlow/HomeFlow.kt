package com.chihuasdevs.cmpsharedui.uiFlows.homeFlow


import com.chihuasdevs.cmpsharedui.CMPSharedUI
import com.chihuasdevs.cmpsharedui.appScreens.homeView.HomeView
import com.chihuasdevs.cmpsharedui.appScreens.homeView.HomeViewModel
import com.chihuasdevs.cmpsharedui.views.flowViews.tabBarView.TabBarView
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

            CMPSharedUI.getAppViewModel()?.setCurrentView({ homeView.ComposableView()}, animation)
        }

    }

    fun resume(animation: UIAnimation?){
        openHome(animation)
    }

    fun getBusinessHomeDelegate(): BusinessHomeDelegate? {
        return homeViewModel
    }
}