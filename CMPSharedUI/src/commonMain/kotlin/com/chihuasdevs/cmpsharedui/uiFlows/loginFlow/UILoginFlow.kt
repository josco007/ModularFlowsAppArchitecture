package com.chihuasdevs.cmpsharedui.uiFlows.loginFlow


import com.chihuasdevs.cmpsharedui.CMPSharedUI
import com.chihuasdevs.cmpsharedui.appScreens.loginView.LoginViewModel
import com.chihuasdevs.cmpsharedui.appScreens.loginView.LoginView
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessLoginDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.LoginUIEventsDelegate

class UILoginFlow {

    private var loginViewModel: LoginViewModel? = null
    private var loginView: LoginView? = null

    fun start(loginUIEventsDelegate: LoginUIEventsDelegate?, animation: UIAnimation?) {
        loginViewModel = LoginViewModel(loginUIEventsDelegate)
        loginViewModel?.let { viewModel ->
             loginView = LoginView(viewModel)

        }
        openLogin(animation)
    }

    fun resume(animation: UIAnimation?){
        openLogin(animation)
    }

    private fun openLogin(animation: UIAnimation?) {
        CMPSharedUI.getAppViewModel()?.setCurrentView({ loginView?.ComposableView() }, animation)
    }

    fun getBusinessLoginDelegate(): BusinessLoginDelegate? {
        return loginViewModel
    }
}