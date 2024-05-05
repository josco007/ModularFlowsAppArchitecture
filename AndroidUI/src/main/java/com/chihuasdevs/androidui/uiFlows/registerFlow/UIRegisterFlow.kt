package com.chihuasdevs.androidui.uiFlows.registerFlow

import com.chihuasdevs.androidui.AndroidUI
import com.chihuasdevs.androidui.base.BaseView
import com.chihuasdevs.androidui.appScreens.passwordView.PasswordView
import com.chihuasdevs.androidui.appScreens.passwordView.PasswordViewModel
import com.chihuasdevs.androidui.appScreens.userInfoView.UserInfoView
import com.chihuasdevs.androidui.appScreens.userInfoView.UserInfoViewModel
import com.chihuasdevs.androidui.appScreens.verificationCodeView.VerificationCodeView
import com.chihuasdevs.androidui.appScreens.verificationCodeView.VerificationCodeViewModel
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessPasswordDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessUserInfoDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessVerificationCodeDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screensGroup.BusinessRegisterGroupFlowDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.PasswordUIEventsDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.UserInfoUIEventsDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.VerificationCodeUIEventsDelegate

class UIRegisterFlow: BusinessRegisterGroupFlowDelegate {

    private var userInfoViewModel: UserInfoViewModel? = null
    private var userInfoView: UserInfoView? = null

    private var passwordUIEventsDelegate: PasswordUIEventsDelegate? = null
    private var passwordViewModel: PasswordViewModel? = null
    private var passwordView: PasswordView? = null

    private var verificationCodeUIEventsDelegate: VerificationCodeUIEventsDelegate? = null
    private var verificationCodeViewModel: VerificationCodeViewModel? = null
    private var verificationCodeView: VerificationCodeView? = null

    private var currentView: BaseView? = null

    fun start(userInfoUIEventsDelegate: UserInfoUIEventsDelegate?,
              passwordUIEventsDelegate: PasswordUIEventsDelegate?,
              verificationCodeUIEventsDelegate: VerificationCodeUIEventsDelegate?,
              animation: UIAnimation?) {

        this.passwordUIEventsDelegate = passwordUIEventsDelegate
        this.verificationCodeUIEventsDelegate = verificationCodeUIEventsDelegate

        userInfoViewModel = UserInfoViewModel(userInfoUIEventsDelegate)
        userInfoViewModel?.let { viewModel ->
            userInfoView = UserInfoView(viewModel)

        }
        openUserInfo(animation)

    }

    fun resume(animation: UIAnimation?){
        AndroidUI.getAppViewModel()?.setCurrentView({ currentView?.ComposableView() }, animation)
    }

    private fun openUserInfo(animation: UIAnimation?) {
        currentView = userInfoView
        AndroidUI.getAppViewModel()?.setCurrentView({ userInfoView?.ComposableView() }, animation)
    }


    fun getBusinessUserInfoDelegate(): BusinessUserInfoDelegate? {
        return userInfoViewModel
    }

    fun getBusinessPasswordDelegate(): BusinessPasswordDelegate? {
        return passwordViewModel
    }

    fun getBusinessVerificationCodeDelegate(): BusinessVerificationCodeDelegate? {
        return verificationCodeViewModel
    }

    override fun openUserPassword(animation: UIAnimation?) {
        if (passwordViewModel == null){
            passwordViewModel = PasswordViewModel(passwordUIEventsDelegate)
            passwordViewModel?.let { viewModel ->
                passwordView = PasswordView(viewModel)

            }
        }

        currentView = passwordView
        AndroidUI.getAppViewModel()?.setCurrentView({ passwordView?.ComposableView() }, animation)
    }

    override fun openVerificationCode(animation: UIAnimation?) {
        if (verificationCodeViewModel == null){
            verificationCodeViewModel = VerificationCodeViewModel(verificationCodeUIEventsDelegate)
            verificationCodeViewModel?.let { viewModel ->
                verificationCodeView = VerificationCodeView(viewModel)

            }
        }

        currentView = verificationCodeView
        AndroidUI.getAppViewModel()?.setCurrentView({ verificationCodeView?.ComposableView() }, animation)
    }
}