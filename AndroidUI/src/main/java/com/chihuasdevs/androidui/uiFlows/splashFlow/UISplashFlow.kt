package com.chihuasdevs.androidui.uiFlows.splashFlow

import com.chihuasdevs.androidui.AndroidUI
import com.chihuasdevs.androidui.appScreens.splashView.SplashView
import com.chihuasdevs.androidui.appScreens.splashView.SplashViewModel
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
            AndroidUI.getAppViewModel()?.setCurrentView({ splashView.ComposableView() },
                UIAnimation.FORWARD)
        }

    }

    fun getBusinessSplashDelegate(): BusinessSplashDelegate? {
        return splashViewModel
    }
}