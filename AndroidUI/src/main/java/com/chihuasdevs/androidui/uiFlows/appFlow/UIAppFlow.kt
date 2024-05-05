package com.chihuasdevs.androidui.uiFlows.appFlow

import com.chihuasdevs.androidui.appScreens.appView.AppView
import com.chihuasdevs.androidui.appScreens.appView.AppViewModel
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessAppViewDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.AppUIEventsDelegate


class UIAppFlow {

    private var appView: AppView? = null
    private var appViewModel: AppViewModel? = null

    fun start(appUIEventsDelegate: AppUIEventsDelegate?){
        appViewModel = AppViewModel(appUIEventsDelegate)

        appViewModel?.let { viewModel ->
            appView = AppView(viewModel)
        }
    }

    fun getAppView(): AppView? {
        return appView
    }

    fun getBusinessAppViewDelegate(): BusinessAppViewDelegate? {
        return appViewModel
    }

    fun getAppViewModel(): AppViewModel? {
        return appViewModel
    }


}