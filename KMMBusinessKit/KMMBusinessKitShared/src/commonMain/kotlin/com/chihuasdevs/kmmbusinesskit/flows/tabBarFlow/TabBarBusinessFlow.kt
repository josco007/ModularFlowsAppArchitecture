package com.chihuasdevs.kmmbusinesskit.flows.tabBarFlow

import com.chihuasdevs.kmmCoreKit.base.BusinessBaseFlow
import com.chihuasdevs.kmmCoreKit.base.CoreKitBaseViewModel
import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.ImageAsset
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.businessToRepo.UserWebservicesDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessAppViewDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessTabBarDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.TabBarUIEventsDelegate
import com.chihuasdevs.kmmCoreKit.models.ui.tabbar.TabBarButtonModel
import com.chihuasdevs.kmmbusinesskit.KMMBusinessKit


class TabBarBusinessFlow: BusinessBaseFlow(), TabBarUIEventsDelegate {
    private var businessKit: KMMBusinessKit? = null
    private var businessTabBarDelegate: BusinessTabBarDelegate? = null

    fun start(businessKit: KMMBusinessKit?,
              businessTabBarDelegate: BusinessTabBarDelegate?){
        this.businessKit = businessKit
        this.businessTabBarDelegate = businessTabBarDelegate

        showTabBarButtons()
    }

    fun showTabBarButtons(){
        val tabBarButtons = mutableListOf<TabBarButtonModel>()
        tabBarButtons.add(TabBarButtonModel("1", "home", ImageAsset.HOME, true))
        tabBarButtons.add(TabBarButtonModel("2", "account", ImageAsset.ACCOUNT, true))
        tabBarButtons.add(TabBarButtonModel("3", "favorite", ImageAsset.FAVORITES, true))

        businessTabBarDelegate?.setTabBarButtons(this, tabBarButtons)
    }

    fun openHome(){

    }

    fun openAccount(){

    }

    fun openFavorites(){

    }


    override fun onTabBarButtonTapped(
        userDataViewModel: CoreKitBaseViewModel?,
        tabBarButtonModel: TabBarButtonModel
    ) {
        when (tabBarButtonModel.id) {
            "1" -> openHome()
            "2" -> openAccount()
            "3" -> openFavorites()
        }
    }

}

