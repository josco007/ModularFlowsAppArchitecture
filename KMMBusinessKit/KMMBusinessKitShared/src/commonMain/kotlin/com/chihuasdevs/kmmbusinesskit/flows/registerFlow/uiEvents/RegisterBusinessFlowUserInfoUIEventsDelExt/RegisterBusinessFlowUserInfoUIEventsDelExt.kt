package com.chihuasdevs.kmmbusinesskit.flows.registerFlow.uiEvents.RegisterBusinessFlowUserInfoUIEventsDelExt

import com.chihuasdevs.kmmCoreKit.base.CoreKitBaseViewModel
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.UserInfoUIEventsDelegate
import com.chihuasdevs.kmmCoreKit.models.ui.userInfo.UserInfo
import com.chihuasdevs.kmmbusinesskit.flows.registerFlow.RegisterBusinessFlow

val RegisterBusinessFlow.userInfoUIEventsDelegate: UserInfoUIEventsDelegate
    get() = object : UserInfoUIEventsDelegate {
        override fun onNextTapped(userDataViewModel: CoreKitBaseViewModel?, userInfo: UserInfo) {
            registerUserRequest?.apply {
                name = userInfo.name
                lastname = userInfo.lastname
                email = userInfo.email
            }

            businessRegisterGroupFlowDelegate?.openUserPassword(UIAnimation.FORWARD)
        }
    }

