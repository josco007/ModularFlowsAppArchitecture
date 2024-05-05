package com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness

import com.chihuasdevs.kmmCoreKit.base.CoreKitBaseViewModel
import com.chihuasdevs.kmmCoreKit.models.ui.tabbar.TabBarButtonModel
import com.chihuasdevs.kmmCoreKit.models.ui.userInfo.UserInfo

interface TabBarUIEventsDelegate {
    fun onTabBarButtonTapped(userDataViewModel: CoreKitBaseViewModel?, tabBarButtonModel: TabBarButtonModel)
}