package com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness

import com.chihuasdevs.kmmCoreKit.base.CoreKitBaseViewModel
import com.chihuasdevs.kmmCoreKit.models.ui.userInfo.UserInfo

interface UserInfoUIEventsDelegate {

    fun onNextTapped(userDataViewModel: CoreKitBaseViewModel?, userInfo: UserInfo)
}