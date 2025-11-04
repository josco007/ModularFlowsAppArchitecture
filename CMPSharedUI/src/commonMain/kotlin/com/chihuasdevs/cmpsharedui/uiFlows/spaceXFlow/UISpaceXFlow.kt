package com.chihuasdevs.cmpsharedui.uiFlows.spaceXFlow


import com.chihuasdevs.cmpsharedui.CMPSharedUI
import com.chihuasdevs.cmpsharedui.appScreens.spaceXView.SpaceXView
import com.chihuasdevs.cmpsharedui.appScreens.spaceXView.SpaceXViewModel
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessSpaceXDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.SpaceXUIEventsDelegate


class UISpaceXFlow {

    private var spaceXViewModel: SpaceXViewModel? = null
    private var spaceXView: SpaceXView? = null

    fun start(spaceXUIEventsDelegate: SpaceXUIEventsDelegate?, animation: UIAnimation?) {
        spaceXViewModel = SpaceXViewModel(spaceXUIEventsDelegate)
        spaceXViewModel?.let { viewModel ->
            spaceXView = SpaceXView(viewModel)

        }
        openSpaceX(animation)
    }

    fun resume(animation: UIAnimation?){
        openSpaceX(animation)
    }

    private fun openSpaceX(animation: UIAnimation?) {
        CMPSharedUI.getAppViewModel()?.setCurrentView({ spaceXView?.ComposableView() }, animation)
    }

    fun getBusinessSpaceXDelegate(): BusinessSpaceXDelegate? {
        return spaceXViewModel
    }
}