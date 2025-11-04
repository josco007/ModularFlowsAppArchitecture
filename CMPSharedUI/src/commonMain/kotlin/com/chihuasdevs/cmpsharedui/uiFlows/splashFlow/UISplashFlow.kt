package com.chihuasdevs.cmpsharedui.uiFlows.splashFlow

import com.chihuasdevs.cmpsharedui.CMPSharedUI
import com.chihuasdevs.cmpsharedui.appScreens.splashView.SplashView
import com.chihuasdevs.cmpsharedui.appScreens.splashView.SplashViewModel
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessSplashDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.SplashUIEventsDelegate

class UISplashFlow {

    private var splashViewModel: SplashViewModel? = null

    fun start(splashUIEventsDelegate: SplashUIEventsDelegate?) {
        splashViewModel = SplashViewModel(splashUIEventsDelegate)
        openSplash()
    }

    private fun openSplash() {
        splashViewModel?.let { viewModel ->
            val splashView = SplashView(viewModel)

            //UISDK.shared.getAppViewModel()?.show(splashView)
            CMPSharedUI.getAppViewModel()?.setCurrentView({ splashView.ComposableView() },
                UIAnimation.FORWARD)
        }

    }

    fun getBusinessSplashDelegate(): BusinessSplashDelegate? {
        return splashViewModel
    }
}