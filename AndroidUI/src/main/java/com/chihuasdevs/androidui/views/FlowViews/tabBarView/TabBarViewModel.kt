package com.chihuasdevs.androidui.views.FlowViews.tabBarView

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chihuasdevs.kmmCoreKit.base.BusinessBaseFlow
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessTabBarDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.TabBarUIEventsDelegate
import com.chihuasdevs.kmmCoreKit.models.ui.tabbar.TabBarButtonModel
import kotlinx.coroutines.launch


class TabBarViewModel(): ViewModel(), BusinessTabBarDelegate {


    var tabBarUIEventsDelegate: TabBarUIEventsDelegate? = null
    var tabBarButtons: MutableState<List<TabBarButtonModel>> = mutableStateOf(emptyList())


    constructor(tabBarUIEventsDelegate: TabBarUIEventsDelegate? = null) : this() {
        this.tabBarUIEventsDelegate = tabBarUIEventsDelegate
    }

    override fun setTabBarButtons(
        businessFlow: BusinessBaseFlow?,
        tabBarButtons: List<TabBarButtonModel>
    ) {
        this.tabBarButtons.value = tabBarButtons
    }

}