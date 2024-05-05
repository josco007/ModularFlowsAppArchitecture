package com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens

import com.chihuasdevs.kmmCoreKit.base.BusinessBaseFlow
import com.chihuasdevs.kmmCoreKit.models.ui.tabbar.TabBarButtonModel

interface BusinessTabBarDelegate {
    fun setTabBarButtons(businessFlow: BusinessBaseFlow?, tabBarButtons: List<TabBarButtonModel>)

}